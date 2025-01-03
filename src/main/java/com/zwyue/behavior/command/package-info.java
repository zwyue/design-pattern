package com.zwyue.behavior.command;

/**
 * 行为型设计模式，命令模式
 *
 * 命令模式，英文全称是是 Command Design Pattern
 *
 * 在 GoF 的《设计模式》一书中，它是这么定义的：
 *  The command pattern encapsulates a request as an object,
 *  thereby letting us parameterize other objects with different requests,
 *  queue or log requests, and support undoable operations.
 *
 *  将请求（命令）封装为一个对象，这样可以使用不同的请求参数化其他对象（将不同请求依赖注入到其他对象），
 *  并且能够支持请求（命令）的排队执行、记录日志、撤销等（附加控制）功能。
 *
 */