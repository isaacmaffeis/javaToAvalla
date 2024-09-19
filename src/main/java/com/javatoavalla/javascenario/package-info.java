/**
 * This package contains classes and interfaces that define and manage the scenarios in a
 * Java environment.
 *
 * <p>The interfaces are:
 * <ul>
 *   <li>{@link com.javatoavalla.javascenario.ScenarioManager},which provides methods to set up
 *   and manage scenarios</li>
 *   <li> {@link com.javatoavalla.javascenario.ScenarioReader} that defines the contract for
 *   reading and parsing Java scenario files. </li>
 * </ul>
 * The package is structured with two sub-packages:
 * <ul>
 *   <li>{@code impl} - Contains the implementation of the scenario management functionality,
 *   including {@link com.javatoavalla.javascenario.impl.ScenarioManagerImpl} and
 *   {@link com.javatoavalla.javascenario.impl.ScenarioReaderImpl}.</li>
 *   <li>{@code listener} - Contains the listener-based implementation for parsing and processing
 *   scenarios, including {@link com.javatoavalla.javascenario.listener.JavaScenarioListener}.</li>
 * </ul>
 */

package com.javatoavalla.javascenario;