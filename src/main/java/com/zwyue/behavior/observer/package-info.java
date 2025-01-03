package com.zwyue.behavior.observer;

/**
 * 行为型设计模式，观察者模式，也被称为发布订阅模式（Publish-Subscribe Design Pattern）。
 *
 * 观察者模式，英文全称是是 Observer Design Pattern
 *
 * 在 GoF 的《设计模式》一书中，它是这么定义的：
 *  Define a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.
 *
 *  在对象之间定义一个一对多的依赖，当一个对象状态改变的时候，所有依赖的对象都会自动收到通知。
 *
 *  一般情况下，被依赖的对象叫作被观察者（Observable），依赖的对象叫作观察者（Observer）。
 *  不过，在实际的项目开发中，这两种对象的称呼是比较灵活的，有各种不同的叫法，
 *  比如：Subject-Observer、Publisher-Subscriber、Producer-Consumer、EventEmitter-EventListener、Dispatcher-Listener。
 *  不管怎么称呼，只要应用场景符合刚刚给出的定义，都可以看作观察者模式。
 */