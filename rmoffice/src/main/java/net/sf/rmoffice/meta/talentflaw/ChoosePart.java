/*
 * Copyright 2012 Daniel Nettesheim
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
package net.sf.rmoffice.meta.talentflaw;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import net.sf.rmoffice.core.TalentFlaw;
import net.sf.rmoffice.meta.INamed;
import net.sf.rmoffice.meta.ISkill;
import net.sf.rmoffice.meta.SkillCategory;
import net.sf.rmoffice.meta.enums.SkillType;
import net.sf.rmoffice.pdf.PDFCreator;
import net.sf.rmoffice.ui.dialog.SelectionDialog;

public class ChoosePart extends AbstractTalentFlawPart {
	private static final String CHOOSE_SKILLBONUS = TalentFlawFactory.registerID("choose_skillbonus");
	private static final String CHOOSE_CATBONUS = TalentFlawFactory.registerID("choose_categorybonus");
	private static final String CHOOSE_SKILLTYPE = TalentFlawFactory.registerID("choose_skilltype");
	private static final String CHOOSE_CATTYPE = TalentFlawFactory.registerID("choose_categorytype");
	
	protected final Object[] selectables;
	protected final Integer bonus;
	protected final SkillType type;
	protected final int amount;
	private final boolean isSkill;
	private final boolean showAllSkills;
	private final boolean showAllSpelllists;

	public ChoosePart(int bonus, int amount, boolean isSkill, boolean showAllSkills, boolean showAllSpelllists, Object... selectables ) {
		this.isSkill = isSkill;
		this.showAllSpelllists = showAllSpelllists;
		this.showAllSkills = showAllSkills;
		this.selectables = selectables;
		this.bonus = Integer.valueOf(bonus);
		this.amount = amount;
		this.type = null;
	}

	public ChoosePart(SkillType type, int amount, boolean isSkill, boolean showAllSkills, boolean showAllSpelllists, Object... selectables) {
		this.isSkill = isSkill;
		this.showAllSpelllists = showAllSpelllists;
		this.showAllSkills = showAllSkills;
		this.selectables = selectables;
		this.type = type;
		this.amount = amount;
		this.bonus = null;
	}
	
	@Override
	public String getId() {
		String id = null;
		if (bonus != null) {
			id = (isSkill ? CHOOSE_SKILLBONUS : CHOOSE_CATBONUS);
		} else if (type != null) {
			id = (isSkill ? CHOOSE_SKILLTYPE : CHOOSE_CATTYPE);
		}
		return id;
	}

	@Override
	public void addToTalentFlaw(TalentFlawContext context, TalentFlaw talentFlaw) {
		Object[] selectableList = selectables;
		if ((showAllSpelllists || showAllSkills) && isSkill) {
			List<Object> allSkills = new ArrayList<Object>();
			for (ISkill skill : context.getSheet().getSkills()) {
				if ( (skill.isSpelllist() && showAllSpelllists) ||
					(!skill.isSpelllist() && showAllSkills) ) {
					allSkills.add(skill);
				}
			}
			selectableList = allSkills.toArray();
		}
		if (isSkill) {
			SelectionDialog<ISkill> dialog = new SelectionDialog<ISkill>(context.getOwner(), amount, (ISkill[])selectableList);
			dialog.setVisible(true);
			for (ISkill skill : dialog.getCheckedItems()) {
				if (type != null) {
					talentFlaw.addSkillType(skill, type);	
				} else if (bonus != null) {
					talentFlaw.addSkillBonus(skill, bonus);
				}
			}
		} else {
			SelectionDialog<SkillCategory> dialog = new SelectionDialog<SkillCategory>(context.getOwner(), amount, (SkillCategory[])selectableList);
			dialog.setVisible(true);
			for (SkillCategory cat : dialog.getCheckedItems()) {
				if (type != null) {
					talentFlaw.addSkillCatType(cat, type);
				} else {
					talentFlaw.addSkillCatBonus(cat, bonus);
				}
			}
		}
	}

	@Override
	public String asText() {
		String what = type != null ? RESOURCE.getString("SkillType."+type.name()) : PDFCreator.format(bonus.intValue(), false);
		StringBuilder from = new StringBuilder();
		if (showAllSkills) {
			from.append(RESOURCE.getString("ui.talentflaw.value.choosefrom.allskill"));
		} 
        if (showAllSpelllists) {
			from.append(RESOURCE.getString("ui.talentflaw.value.choosefrom.allspelllists"));
		}
        if (selectables != null && selectables.length > 0) {
        	for (Object val : selectables) {
				if (from.length() > 0) {
					from.append(", ");
				}
				if (val instanceof INamed) {
					from.append(((INamed)val).getName());
				}
			}
		}
		return MessageFormat.format(RESOURCE.getString("ui.talentflaw.value.choosefrom"), ""+amount, from.toString(), what );
	}

}
