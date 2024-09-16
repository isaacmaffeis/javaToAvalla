/**
 * This package contains classes and interfaces that define and manage the scenarios in a
 * Java environment.
 * <p>
 * The interfaces are:
 * <ul>
 *   <li>{@link org.javaToAvalla.javaScenario.ScenarioManagerIF},which provides methods to set up
 *   and manage scenarios</li>
 *   <li> {@link org.javaToAvalla.javaScenario.ScenarioReaderIF} that defines the contract for
 *   reading and parsing Java scenario files. </li>
 * </ul>
 * The package is structured with two sub-packages:
 * <ul>
 *   <li>{@code impl} - Contains the implementation of the scenario management functionality,
 *   including {@link org.javaToAvalla.javaScenario.impl.ScenarioManager} and
 *   {@link org.javaToAvalla.javaScenario.impl.ScenarioReader}.</li>
 *   <li>{@code listener} - Contains the listener-based implementation for parsing and processing
 *   scenarios, including {@link org.javaToAvalla.javaScenario.listener.JavaScenarioListener}.</li>
 * </ul>
 */

package org.javaToAvalla.javaScenario;