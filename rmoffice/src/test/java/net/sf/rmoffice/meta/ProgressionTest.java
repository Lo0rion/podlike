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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import net.sf.rmoffice.meta.Progression;

import org.junit.Test;



public class ProgressionTest {
	@Test
	public void testBonus() {
		IProgression p = new Progression(-5, 5, 4, 3, 2);
		assertEquals(-5, p.getBonus(0));
		int b = 0;
		for (int i=1; i<=10; i++) {
			b += 5;
			assertEquals(b, p.getBonus(i));
		}
		for (int i=11; i<=20; i++) {
			b += 4;
			assertEquals(b, p.getBonus(i));
		}
		for (int i=21; i<=30; i++) {
			b += 3;
			assertEquals(b, p.getBonus(i));
		}
		for (int i=31; i<50; i++) {
			b += 2;
			assertEquals(b, p.getBonus(i));
		}
	}
	
	@Test
	public void testCompare() {
		Progression p1 = new Progression(-5, 5, 4, 3, 2);
		Progression p2 = new Progression(0, 5, 4, 3, 2);
		Progression p3 = new Progression(0, 5, 4, 3, 1);		
		Progression p4 = new Progression(0, 5, 3, 2, 2);
		assertTrue( p1.compareTo(p1) == 0 );
		assertTrue( p1.compareTo(p2) < 0 );
		assertTrue( p2.compareTo(p3) > 0 );
		assertTrue( p3.compareTo(p2) < 0 );
		assertTrue( p2.compareTo(p4) > 0 );
		assertTrue( p4.compareTo(p2) < 0 );
		assertTrue( p3.compareTo(p4) > 0 );
		assertTrue( p4.compareTo(p3) < 0 );
	}
}
