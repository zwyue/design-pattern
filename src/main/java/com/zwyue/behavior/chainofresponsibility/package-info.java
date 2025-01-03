package com.zwyue.behavior.chainofresponsibility;

/**
 * 行为型设计模式，职责链模式
 *
 * 职责链模式，英文全称是是 Chain Of Responsibility Design Pattern
 *
 * 在 GoF 的《设计模式》一书中，它是这么定义的：
 *  Avoid coupling the sender of a request to its receiver by giving more than one object a chance to handle the request.
 *  Chain the receiving objects and pass the request along the chain until an object handles it.
 *
 *  将请求的发送和接收解耦，让多个接收对象都有机会处理这个请求。
 *  将这些接收对象串成一条链，并沿着这条链传递这个请求，直到链上的某个接收对象能够处理它为止。
 *
 * 职责链模式常用在框架的开发中，为框架提供扩展点，让框架的使用者在不修改框架源码的情况下，基于扩展点添加新的功能。
 * 实际上，更具体点来说，职责链模式最常用来开发框架的过滤器和拦截器。
 *
 *  web filter 作用于容器，应用范围影响最大；
 *  spring interceptor 作用于框架，范围影响适中；
 *  aop 作用于业务逻辑，精细化处理，范围影响最小。Spring AOP 是基于代理模式来实现的。
 */