/*
 * Copyright 2012 Daniel Golesny
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.sf.rmoffice.meta;

import java.util.HashMap;
import java.util.Map;

import net.sf.rmoffice.meta.enums.LengthUnit;
import net.sf.rmoffice.meta.enums.RaceScope;



/**
 * 
 */
public class Skill implements ISkill {	
	private Integer id;
	private String name;
	private SkillCategory category;
	private Map<LengthUnit, String> descriptions;
	private RaceScope scope;
	private String source;
	private Integer orderGroup;
	
	/* package */ Skill() {
	}
	
	/** {@inheritDoc} */
	@Override
	public Integer getId() {
		return id;
	}

	/* package */ void setId(Integer id) {
		this.id = id;
	}

	/** {@inheritDoc} */
	@Override
	public String getName() {
		return name;
	}
	
	/* package */void setName(String name) {
		this.name = name;
	}
	
	public SkillCategory getCategory() {
		return category;
	}
	
	/* package */void setCategory(SkillCategory category) {
		this.category = category;
	}
	
	/** {@inheritDoc} */
	@Override
	public String getDescription(LengthUnit lengthUnit) {
		String descr = null;
		if (descriptions != null) {
			if (descriptions.containsKey(lengthUnit)) {
				descr = descriptions.get(lengthUnit);
			} else {
				descr = descriptions.get(null);
			}
		}
		return descr;
	}
	
	/**
	 * 
	 * @param lengthUnit may be {@code null}
	 * @param description the description
	 */
	/* package */void setDescription(LengthUnit lengthUnit, String description) {
		if (descriptions == null) {
			descriptions = new HashMap<LengthUnit, String>();
		}
		this.descriptions.put(lengthUnit, description);
	}
	
	/** {@inheritDoc} */
	@Override
	public boolean isSpelllist() {
		return false;
	}
	
	/** {@inheritDoc} */
	@Override
	public String toString() {
		return name;
	}

	/**
	 * Sets the {@link RaceScope} of the skill
	 * @param scope
	 */
	/* package */ void setScope(RaceScope scope) {
		this.scope = scope;
	}

	/**
	 * Returns the scope of the skill or {@code null} if not race is available.
	 * 
	 * @return the scope
	 */
	@Override
	public RaceScope getScope() {
		return scope;
	}

	@Override
	public String getSource() {
		return source;
	}

	
	/* package */ void setSource(String source) {
		this.source = source;
	}
	
	@Override
	public Integer getOrderGroup() {
		if (orderGroup == null) {
			return Integer.valueOf(0);
		}
		return orderGroup;
	}

	@Override
	public void setOrderGroup(Integer orderGroup) {
		this.orderGroup = orderGroup;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Skill other = (Skill) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
