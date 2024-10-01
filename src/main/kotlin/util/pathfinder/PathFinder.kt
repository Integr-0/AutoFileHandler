/*
 * Copyright © 2024 Integr
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package util.pathfinder


class PathFinder(private val worldState: List<List<Boolean>>) {
    private var allowDiagonal = false
    private var maxSteps = 10000

    fun getPathTo(start: Pair<Int, Int>, end: Pair<Int, Int>): List<Pair<Int, Int>> {
        // a*
        val openList = mutableListOf<PathNode>()
        val closedList = mutableListOf<PathNode>()

        openList.add(PathNode(start.first, start.second).setCost(end, start))

        println("   ● Starting pathfinder from $start to $end")

        val st = System.currentTimeMillis()

        while (openList.isNotEmpty()) {
            // open new nodes
            val currentNode = openList.minByOrNull { it.f }!!

            if (currentNode.x == end.first && currentNode.y == end.second) {
                val path = mutableListOf<Pair<Int, Int>>()
                var current: PathNode? = currentNode
                while (current != null) {
                    path.add(Pair(current.x, current.y))
                    current = current.parent
                }

                val delta = System.currentTimeMillis() - st

                println("   ✓ Path successfully found (${delta}ms).")

                return path.reversed()
            } else {
                openList.remove(currentNode)
                closedList.add(currentNode)

                for (i in -1..1) {
                    for (j in -1..1) {
                        if (i == 0 && j == 0) continue
                        if (!allowDiagonal && i != 0 && j != 0) continue

                        val x = currentNode.x + i
                        val y = currentNode.y + j

                        if (x < 0 || y < 0 || y >= worldState.size || x >= worldState[y].size) continue
                        if (worldState[y][x]) continue

                        if (closedList.any { it.x ==  x && it.y == y }) continue
                        if (openList.any { it.x ==  x && it.y == y }) continue

                        val neighbour = PathNode(x, y).setCost(end, start).setParent(currentNode)
                        openList.add(neighbour)
                    }
                }
            }
        }

        val delta = System.currentTimeMillis() - st

        println("   ✕ No path found (${delta}ms).")

        return listOf()
    }

    fun allowDiagonal(): PathFinder {
        allowDiagonal = true
        return this
    }

    fun setMaxSteps(maxSteps: Int): PathFinder {
        this.maxSteps = maxSteps
        return this
    }

    companion object {
        fun parseWorldState(str: List<String>, solidSymbol: Char): List<List<Boolean>> {
            return str.map { row ->
                row.map { cell ->
                    cell == solidSymbol
                }
            }
        }
    }
}