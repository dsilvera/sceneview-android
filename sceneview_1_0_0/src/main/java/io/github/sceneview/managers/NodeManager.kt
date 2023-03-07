package io.github.sceneview.managers

import com.google.android.filament.Engine
import io.github.sceneview.Entity
import io.github.sceneview.nodes.CameraNode
import io.github.sceneview.nodes.LightNode
import io.github.sceneview.nodes.ModelNode
import io.github.sceneview.nodes.Node
import java.util.concurrent.ConcurrentHashMap

class NodeManager(val engine: Engine) {

    val entityNodes = ConcurrentHashMap<Entity, Node>()

    fun hasComponent(e: Entity) = getNode(e) != null

    fun getNode(e: Entity): Node? = entityNodes[e]

    fun addComponent(e: Entity, node: Node) {
        entityNodes[e] = node
    }

    fun removeComponent(e: Entity) {
        entityNodes.remove(e)
    }

    fun getComponentCount() = entityNodes.size

    fun empty(): Boolean = entityNodes.isEmpty()

    fun getEntity(node: Node): Entity? = entityNodes.entries.firstOrNull { it.value == node }?.key

    operator fun get(entity: Entity) = entityNodes[entity]

    fun destroyNode(e: Entity) {
        entityNodes[e]?.destroy()
    }

    fun destroy() {
        entityNodes.keys.forEach { entity ->
            destroyNode(entity)
        }
        entityNodes.clear()
    }
}