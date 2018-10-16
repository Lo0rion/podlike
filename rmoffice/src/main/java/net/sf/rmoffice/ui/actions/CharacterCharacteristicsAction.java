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
package net.sf.rmoffice.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.jgoodies.binding.beans.BeanAdapter;

import net.sf.rmoffice.core.Characteristics;
import net.sf.rmoffice.core.RMSheet;
import net.sf.rmoffice.generator.CharacterGenerator;
import net.sf.rmoffice.meta.MetaData;


/**
 * 
 */
public class CharacterCharacteristicsAction implements ActionListener {

	
	private final MetaData data;
	private final BeanAdapter<Characteristics> characteristics;
	private final BeanAdapter<RMSheet> sheetAdapter;

	public CharacterCharacteristicsAction(MetaData data, final BeanAdapter<Characteristics> characteristics, final BeanAdapter<RMSheet> sheetAdapter) {
		this.data = data;
		this.characteristics = characteristics;
		this.sheetAdapter = sheetAdapter;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		CharacterGenerator gen = new CharacterGenerator(characteristics, sheetAdapter, data);
		gen.generateCharacteristics();
	}

}
