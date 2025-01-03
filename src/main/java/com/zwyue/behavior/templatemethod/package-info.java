package com.zwyue.behavior.templatemethod;

/**
 * 行为型设计模式，模板模式
 * 模板模式主要是用来解决 复用 和 扩展 两个问题
 *
 * 模板模式，全称是模板方法设计模式，英文是 Template Method Design Pattern
 *
 * 在 GoF 的《设计模式》一书中，它是这么定义的：
 *  Define the skeleton of an algorithm in an operation, deferring some steps to subclasses.
 *  Template Method lets subclasses redefine certain steps of an algorithm without changing the algorithm’s structure.
 *
 *  模板方法模式在一个方法中定义一个算法骨架，并将某些步骤推迟到子类中实现。模板方法模式可以让子类在不改变算法整体结构的情况下，重新定义算法中的某些步骤。
 *
 * 另外一个技术概念，也能起到跟模板模式相同的作用，那就是回调（Callback）
 * 回调可以分为同步回调和异步回调（或者延迟回调）。同步回调指在函数返回之前执行回调函数；异步回调指的是在函数返回之后执行回调函数。
 * 同步回调从应用场景上很像模板模式，所以，在命名上，许多回调方法类使用 Template（模板）这个单词作为后缀。
 */