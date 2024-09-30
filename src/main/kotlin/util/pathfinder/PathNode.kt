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

import kotlin.math.abs

class PathNode(val x: Int, val y: Int) {
    var f = 0
    var g = 0
    var h = 0

    var parent: PathNode? = null


    fun setCost(end: Pair<Int, Int>, start: Pair<Int, Int>): PathNode {
        setGCost(start)
        setHCost(end)
        f = g + h

        return this
    }

    fun setParent(parent: PathNode): PathNode {
        this.parent = parent
        return this
    }

    private fun setGCost(start: Pair<Int, Int>) {
        g = abs(x - start.first) + abs(y - start.second)
    }

    private fun setHCost(end: Pair<Int, Int>) {
        h = abs(x - end.first) + abs(y - end.second)
    }

}