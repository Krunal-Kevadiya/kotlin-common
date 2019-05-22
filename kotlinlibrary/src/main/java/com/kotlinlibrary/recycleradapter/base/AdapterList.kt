package com.kotlinlibrary.recycleradapter.base

import java.util.*
import kotlin.collections.ArrayList

open class AdapterList<E>(internal var newList: MutableList<E> = mutableListOf()) : List<E> {
    private var oldList: MutableList<E>? = null

    override val size: Int
        get() = newList.size

    override fun contains(element: E): Boolean = newList.contains(element)

    override fun containsAll(elements: Collection<E>): Boolean = newList.containsAll(elements)

    override operator fun get(index: Int): E = newList[index]

    override fun indexOf(element: E): Int = newList.indexOf(element)

    override fun isEmpty(): Boolean = newList.isEmpty()

    override fun iterator(): MutableIterator<E> = newList.iterator()

    override fun lastIndexOf(element: E): Int = newList.lastIndexOf(element)

    override fun listIterator(): MutableListIterator<E> = newList.listIterator()

    override fun listIterator(index: Int): MutableListIterator<E> = newList.listIterator(index)

    override fun subList(fromIndex: Int, toIndex: Int): MutableList<E> {
        return newList.subList(fromIndex, toIndex)
    }

    private fun cacheOldItems() {
        oldList = newList.toMutableList()
    }

    private fun getDiffUtils() = DiffUtilCallback(oldList as ArrayList, newList)

    internal fun recycle() {
        oldList = null
    }

    internal fun reset(elements: MutableList<E>): DiffUtilCallback<E> {
        cacheOldItems()
        newList = elements
        return getDiffUtils()
    }

    internal fun add(element: E): DiffUtilCallback<E> {
        cacheOldItems()
        newList.add(element)
        return getDiffUtils()
    }

    internal fun add(index: Int, element: E): DiffUtilCallback<E> {
        cacheOldItems()
        newList.add(index, element)
        return getDiffUtils()
    }

    internal fun addAll(index: Int, elements: Collection<E>): DiffUtilCallback<E> {
        cacheOldItems()
        newList.addAll(index, elements)
        return getDiffUtils()
    }

    internal fun addAll(elements: Collection<E>): DiffUtilCallback<E> {
        cacheOldItems()
        newList.addAll(elements)
        return getDiffUtils()
    }

    internal fun clear(): DiffUtilCallback<E> {
        cacheOldItems()
        newList.clear()
        return getDiffUtils()
    }

    internal fun remove(element: E): DiffUtilCallback<E> {
        cacheOldItems()
        newList.remove(element)
        return getDiffUtils()
    }

    internal fun removeAll(elements: Collection<E>): DiffUtilCallback<E> {
        cacheOldItems()
        newList.removeAll(elements)
        return getDiffUtils()
    }

    internal fun removeAt(index: Int): DiffUtilCallback<E> {
        cacheOldItems()
        newList.removeAt(index)
        return getDiffUtils()
    }

    internal fun retainAll(elements: Collection<E>): DiffUtilCallback<E> {
        cacheOldItems()
        newList.retainAll(elements)
        return getDiffUtils()
    }

    internal fun set(index: Int, element: E): DiffUtilCallback<E> {
        cacheOldItems()
        newList[index] = element
        return getDiffUtils()
    }

    internal fun swap(firstIndex: Int, secondIndex: Int): DiffUtilCallback<E> {
        cacheOldItems()
        Collections.swap(newList, firstIndex, secondIndex)
        return getDiffUtils()
    }
}