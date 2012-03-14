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
package net.sf.rmoffice.ui.converters;

import java.util.ResourceBundle;

import com.jgoodies.binding.value.AbstractConverter;
import com.jgoodies.binding.value.ValueModel;

public class EnumValueConverter extends AbstractConverter {
	private static final long serialVersionUID = 1L;
	private final static ResourceBundle RESOURCE = ResourceBundle.getBundle("conf.i18n.locale"); //$NON-NLS-1$
	
	public EnumValueConverter(ValueModel subject) {
		super(subject);
	}

	@Override
	public void setValue(Object newValue) {
		/* from ui*/
	}

	@Override
	public Object convertFromSubject(Object subjectValue) {
		/* to ui */
		return RESOURCE.getString(subjectValue.getClass().getSimpleName()+"."+((Enum<?>)subjectValue).name());
	}

}
