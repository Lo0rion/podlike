/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.sf.rmoffice.ui.renderer;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import net.sf.rmoffice.core.items.MagicalFeature;


/**
 * 
 */
public class MagicalFeatureTableCellRenderer extends DefaultTableCellRenderer {
	private static final long serialVersionUID = 1L;

	/** {@inheritDoc} */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		String text = "";
		if (value instanceof MagicalFeature) {
			text = ((MagicalFeature)value).getDescription();
		}
		return super.getTableCellRendererComponent(table, text, isSelected, hasFocus, row, column);
	}

}