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
package net.sf.rmoffice.meta.talentflaw;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import net.sf.rmoffice.meta.MetaData;
import net.sf.rmoffice.meta.talentflaw.parser.BaseMoveRateParser;
import net.sf.rmoffice.meta.talentflaw.parser.ChooseParser;
import net.sf.rmoffice.meta.talentflaw.parser.ChooseSkillParser;
import net.sf.rmoffice.meta.talentflaw.parser.DBParser;
import net.sf.rmoffice.meta.talentflaw.parser.DescriptionParser;
import net.sf.rmoffice.meta.talentflaw.parser.ExhaustionParser;
import net.sf.rmoffice.meta.talentflaw.parser.ResistanceParser;
import net.sf.rmoffice.meta.talentflaw.parser.ITalentFlawPartParser;
import net.sf.rmoffice.meta.talentflaw.parser.InitiativeParser;
import net.sf.rmoffice.meta.talentflaw.parser.BonusParser;
import net.sf.rmoffice.meta.talentflaw.parser.ProgressionParser;
import net.sf.rmoffice.meta.talentflaw.parser.RankParser;
import net.sf.rmoffice.meta.talentflaw.parser.ShieldDBParser;
import net.sf.rmoffice.meta.talentflaw.parser.SkillTypeParser;
import net.sf.rmoffice.meta.talentflaw.parser.StatBonusParser;
import net.sf.rmoffice.meta.talentflaw.parser.WeightPenaltyParser;

/**
 * Factory for creating {@link ITalentFlawPart}.
 */
public class TalentFlawFactory {
	
	private static final Set<String> partIDs = new HashSet<String>();
	private final MetaData metaData;
	private final List<ITalentFlawPartParser<?>> parsers = new ArrayList<ITalentFlawPartParser<?>>();
	
	/**
	 * @param metaData the meta data, not null
	 */
	public TalentFlawFactory(MetaData metaData) {
		this.metaData = metaData;
		initParser();
	}

	/**
	 * Registers the given partID to be shown in the UI.
	 * 
	 * @param partID the part ID
	 * @return the part ID
	 */
	/* package */ static String registerID(String partID) {
		partIDs.add(partID);
		return partID;
	}
	
	/**
	 * Returns an unmodifiable set of registered IDs.
	 * 
	 * @return set of part IDs, not {@code null}
	 */
	public static Set<String> getPartIDs() {
		return Collections.unmodifiableSet(partIDs);
	}
	
	private void initParser() {
		// FIXME find a nicer solution to "register" the parsers
		parsers.add(new InitiativeParser());
		parsers.add(new DescriptionParser());
		parsers.add(new BonusParser(metaData)); 
		parsers.add(new SkillTypeParser(metaData)); 
		parsers.add(new ChooseParser(metaData));
		parsers.add(new ChooseSkillParser(metaData));
		parsers.add(new ProgressionParser());
		parsers.add(new RankParser());
		parsers.add(new WeightPenaltyParser());
		parsers.add(new BaseMoveRateParser());
		parsers.add(new ResistanceParser());
		parsers.add(new DBParser());
		parsers.add(new ShieldDBParser());
		parsers.add(new ExhaustionParser());
		parsers.add(new StatBonusParser());
	}

	/**
	 * Creates a {@link ITalentFlawPart} from the given string.
	 * 
	 * @param partAsString the part as string, may be {@code null}
	 */
	public ITalentFlawPart parseTalentFlawPart(String partAsString) {
		for (Iterator<ITalentFlawPartParser<?>> it = parsers.iterator(); it.hasNext(); ){
			ITalentFlawPartParser<?> parser = it.next();
			if (parser.isParseable(partAsString)) {
				return parser.parse(partAsString);
			}
		}
		throw new IllegalArgumentException("Could not find a parser for '"+partAsString+"'");
	}
}
