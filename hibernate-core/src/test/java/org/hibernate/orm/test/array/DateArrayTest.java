/*
 * Hibernate Search, full-text search for your domain model
 *
 * License: GNU Lesser General Public License (LGPL), version 2.1 or later
 * See the lgpl.txt file in the root directory or <http://www.gnu.org/licenses/lgpl-2.1.html>.
 */
package org.hibernate.orm.test.array;

import java.util.Date;

import org.hibernate.testing.junit4.BaseCoreFunctionalTestCase;
import org.junit.Test;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OrderColumn;

public class DateArrayTest extends BaseCoreFunctionalTestCase {

	@Override
	protected Class<?>[] getAnnotatedClasses() {
		return new Class[] { DateArrayEntity.class };
	}

	@Test
	public void run() throws InterruptedException {
		inTransaction( session -> {
			DateArrayEntity entity = new DateArrayEntity();
			entity.setDates( new Date[] { new Date() } );
			session.persist( entity );
		} );
	}

	@Entity(name = "DateArrayEntity")
	public class DateArrayEntity {

		@Id
		@GeneratedValue
		private Long id;

		@ElementCollection
		@OrderColumn
		@Column(name = "dates_column")
		private Date[] dates = new Date[0];

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Date[] getDates() {
			return dates;
		}

		public void setDates(Date[] dates) {
			this.dates = dates;
		}
	}

}
