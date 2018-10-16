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


import net.sf.rmoffice.RMPreferences;
import net.sf.rmoffice.core.TalentFlaw;
import net.sf.rmoffice.meta.enums.LengthUnit;

public class BaseMoveRatePart extends KeyValuePart {
	public static final String ID = TalentFlawFactory.registerID("basemoverate");
	public BaseMoveRatePart(float factor) {
		super(factor);
	}
	
	@Override
	public String getId() {
		return ID;
	}

	@Override
	public void addToTalentFlaw(TalentFlawContext context, TalentFlaw talentFlaw) {
		talentFlaw.setBaseMovement(Float.valueOf(value));
	}

	@Override
	public String asText() {
		return formatLU(value);
	}
	
	public static String formatLU(float value) {
		LengthUnit lu = RMPreferences.getInstance().getLengthUnit();
		int converted = LengthUnit.CM.convertTo(Math.round(value), lu);
		return lu.getFormattedString(converted);
	}
}
