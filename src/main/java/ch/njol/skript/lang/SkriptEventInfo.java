/*
 *   This file is part of Skript.
 *
 *  Skript is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Skript is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Skript.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * 
 * Copyright 2011, 2012 Peter Güttinger
 * 
 */

package ch.njol.skript.lang;

import java.util.Locale;

import org.bukkit.event.Event;

public final class SkriptEventInfo<E extends SkriptEvent> extends SyntaxElementInfo<E> {
	
	public Class<? extends Event>[] events;
	public final String name;
	
	private final String id;
	private String[] description;
	private String[] examples;
	private String since;
	
	/**
	 * @param name
	 * @param patterns
	 * @param c
	 * @param events
	 * @throws IllegalArgumentException
	 */
	public SkriptEventInfo(String name, final String[] patterns, final Class<E> c, final Class<? extends Event>[] events) throws IllegalArgumentException {
		super(patterns, c);
		assert name != null;
		assert patterns != null && patterns.length > 0;
		assert c != null;
		assert events != null && events.length > 0;
		
		this.events = events;
		
		if (name.startsWith("*")) {
			this.name = name = name.substring(1);
		} else {
			this.name = "On " + name;
		}
		this.id = name.toLowerCase(Locale.ENGLISH).replaceAll("[#'\"<>/&]", "").replaceAll("\\s+", "_");
	}
	
	/**
	 * Only used for Skript's documentation.
	 * 
	 * @param description
	 * @return
	 */
	public SkriptEventInfo<E> description(final String... description) {
		assert this.description == null;
		this.description = description;
		return this;
	}
	
	/**
	 * Only used for Skript's documentation.
	 * 
	 * @param examples
	 * @return
	 */
	public SkriptEventInfo<E> examples(final String... examples) {
		assert this.examples == null;
		this.examples = examples;
		return this;
	}
	
	/**
	 * Only used for Skript's documentation.
	 * 
	 * @param since
	 * @return
	 */
	public SkriptEventInfo<E> since(final String since) {
		assert this.since == null;
		this.since = since;
		return this;
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String[] getDescription() {
		return description;
	}
	
	public String[] getExamples() {
		return examples;
	}
	
	public String getSince() {
		return since;
	}
}