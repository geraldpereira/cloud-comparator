package fr.byob.cloud.comparator.gwt.client.widget;

import java.util.Comparator;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.IdentityColumn;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

import fr.byob.cloud.comparator.gwt.client.widget.constants.StaticConstants;
import fr.byob.cloud.comparator.gwt.client.widget.listener.IListenerTable;
import fr.byob.cloud.comparator.gwt.client.widget.model.ResultModel;
import fr.byob.cloud.comparator.gwt.client.widget.model.ResultRowModel;
import fr.byob.cloud.comparator.gwt.client.widget.renderer.HeaderTable;
import fr.byob.cloud.comparator.gwt.client.widget.renderer.LocationCell;
import fr.byob.cloud.comparator.gwt.client.widget.renderer.PriceCell;
import fr.byob.cloud.comparator.gwt.client.widget.renderer.ProviderCell;

public class ResultPanel extends SimplePanel implements IListenerTable {

	private final StaticConstants staticConstants = GWT.create(StaticConstants.class);

	// Create a data provider.
	private final ListDataProvider<ResultRowModel> dataProvider = new ListDataProvider<ResultRowModel>();

	private final ResultModel model;
	private final Label messageLabel;
	private final SimplePager pager;
	private final CellTable<ResultRowModel> table;

	public ResultPanel(final ResultModel model) {
		this.model = model;
		model.addListener(this);
		messageLabel = new Label();
		messageLabel.addStyleName("cloud-result-message");

		// Create a CellTable.
		table = new CellTable<ResultRowModel>();
		table.addStyleName("cloud-result-table");
		// Create provider column.
		final IdentityColumn<ResultRowModel> providerColumn = new IdentityColumn<ResultRowModel>(
				new ProviderCell());
		providerColumn.setSortable(true);

		// Create provider column.
		final IdentityColumn<ResultRowModel> locationColumn = new IdentityColumn<ResultRowModel>(new LocationCell());
		locationColumn.setSortable(true);

		// Create cost column.
		final IdentityColumn<ResultRowModel> costColumn = new IdentityColumn<ResultRowModel>(new PriceCell());
		costColumn.setSortable(true);

		// Add the columns.
		table.addColumn(providerColumn, new HeaderTable(staticConstants.result_panel_col_provider()));
		table.addColumn(locationColumn, new HeaderTable(staticConstants.result_panel_col_server()));
		table.addColumn(costColumn, new HeaderTable(staticConstants.result_panel_col_price()));

		// Connect the table to the data provider.
		dataProvider.addDataDisplay(table);

		final ListHandler<ResultRowModel> sortHandler = new ListHandler<ResultRowModel>(dataProvider.getList());
		table.addColumnSortHandler(sortHandler);

		sortHandler.setComparator(providerColumn, new Comparator<ResultRowModel>() {
			@Override
			public int compare(final ResultRowModel o1, final ResultRowModel o2) {
				if (o1 == o2) {
					return 0;
				}

				// Compare the name columns.
				if (o1 != null) {
					return o2 != null ? o1.getProviderName().compareTo(o2.getProviderName()) : 1;
				}
				return -1;
			}
		});

		sortHandler.setComparator(locationColumn, new Comparator<ResultRowModel>() {
			@Override
			public int compare(final ResultRowModel o1, final ResultRowModel o2) {
				if (o1 == o2) {
					return 0;
				}

				// Compare the name columns.
				if (o1 != null) {
					return o2 != null ? o1.getLocation().compareTo(o2.getLocation()) : 1;
				}
				return -1;
			}
		});

		sortHandler.setComparator(costColumn, new Comparator<ResultRowModel>() {
			@Override
			public int compare(final ResultRowModel o1, final ResultRowModel o2) {
				if (o1 == o2) {
					return 0;
				}

				// Compare the name columns.
				if (o1 != null && o2 != null) {
					return (int) (o1.getCost() - o2.getCost());
				}
				return -1;
			}
		});

		// Create a pager
		final SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
		pager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
		pager.setDisplay(table);
		pager.setPageSize(5);

		// Add it to the root panel.
		final VerticalPanel tabler = new VerticalPanel();
		tabler.add(table);
		tabler.add(pager);
		tabler.add(messageLabel);
		tabler.setCellHorizontalAlignment(pager, HasHorizontalAlignment.ALIGN_CENTER);
		tabler.setCellHorizontalAlignment(messageLabel, HasHorizontalAlignment.ALIGN_CENTER);
		tabler.addStyleName("cloud-result-tabler");

		table.setVisible(false);
		pager.setVisible(false);

		this.setWidget(tabler);
		this.addStyleName("cloud-result");
	}


	@Override
	public void refreshDatas() {
		// Add the data to the data provider, which automatically pushes it to the
		// widget.
		final List<ResultRowModel> resultList = model.getResultList();
		final List<ResultRowModel> list = dataProvider.getList();
		list.clear();
		if (resultList.isEmpty()) {
			table.setVisible(false);
			pager.setVisible(false);
		} else {
			for (final ResultRowModel contact : resultList) {
				list.add(contact);
			}
			table.setVisible(true);
			pager.setVisible(true);
		}
		messageLabel.setText(model.getMessage());
	}

}
