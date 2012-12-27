package fr.byob.cloud.comparator.gwt.client.widget.model;

import java.io.Serializable;

public class SimpleProviderModel implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = -3087231493007659164L;
		
		private String providerName;
		private Double totalCost;

		public String getProviderName() {
			return providerName;
		}

		public void setProviderName(final String providerName) {
			this.providerName = providerName;
		}

		public Double getTotalCost() {
			return totalCost;
		}

		public void setTotalCost(final Double totalCost) {
			this.totalCost = totalCost;
		}

	}
