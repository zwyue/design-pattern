package com.zwyue.behavior.memento;

/**
 * 行为型设计模式，备忘录模式，也叫快照（Snapshot）模式
 *
 * 备忘录模式，英文全称是是 Memento Design Pattern
 *
 * 在 GoF 的《设计模式》一书中，它是这么定义的：
 *  Captures and externalizes an object’s internal state so that it can be restored later, all without violating encapsulation.
 *
 *  在不违背封装原则的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态，以便之后恢复对象为先前的状态。
 *
 *  备忘录是对源对象的快照，也就是给拥有状态的对象的备份。
 *  使用一个新的类也就是快照类，获取源对象的内部状态，提供给外部使用，从而不影响源对象的使用，满足封装的特性
 *
 * 除了备忘录模式，还有一个跟它很类似的概念，“备份”，它在我们平时的开发中更常听到。
 * 那备忘录模式跟“备份”有什么区别和联系呢？实际上，这两者的应用场景很类似，都应用在防丢失、恢复、撤销等场景中。
 * 它们的区别在于，备忘录模式更侧重于代码的设计和实现，备份更侧重架构设计或产品设计。
 */