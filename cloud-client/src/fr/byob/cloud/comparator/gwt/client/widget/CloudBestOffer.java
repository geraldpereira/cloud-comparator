package fr.byob.cloud.comparator.gwt.client.widget;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import fr.byob.cloud.comparator.gwt.client.widget.listener.IListenerTable;
import fr.byob.cloud.comparator.gwt.client.widget.model.BestProviderModel;
import fr.byob.cloud.comparator.gwt.client.widget.model.SimpleProviderModel;
import fr.byob.cloud.comparator.gwt.client.widget.utils.WidgetFactory;

public class CloudBestOffer extends VerticalPanel implements IListenerTable{
	private final BestProviderModel model ;
	private FlexTable grid ;

	public CloudBestOffer(final BestProviderModel model) {
		this.setSpacing(8);
		this.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		this.model = model;
		model.addListener(this);
		initTabWidget();
	}
	List<String> titles = new ArrayList<String>(3);
	{
		titles.add("Best offer by provider");
		titles.add("Best offer by provider 1");
		titles.add("Best offer by provider 2");
	}
	private void initTabWidget(){
		grid = new FlexTable();
		final FlexCellFormatter cellFormatter = grid.getFlexCellFormatter();
		grid.setCellSpacing(4);
		grid.setWidth("500px");
		final Label title = new Label("Best offer by provider");
		title.setStyleName("gwt-Label-Title");
		grid.setWidget(0, 0, title);
		cellFormatter.setColSpan(0, 0, 2);
		cellFormatter.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);

		final DecoratorPanel decPanel = new DecoratorPanel();
		decPanel.setWidget(grid);
		this.setTitle("Best offer by provider");
		this.add(decPanel);
		this.setVisible(false);
	}
	@Override
	public void refreshDatas() {
		if(model.getResultList().size() > 0){
			final int numColumns = 2;
			final int numRows = model.getResultList().size()/2 +1;
			int count = 0;
			for (int row = 1 ; row < numRows ; row++) {
				for (int col = 0 ; col < numColumns ; col++) {
					final SimpleProviderModel provider = model.getResultList().get(count);
					final HorizontalPanel providerPanel = new HorizontalPanel();
					providerPanel.setSpacing(6);
					final Image imgProvider = new Image(/*CloudConstants.getProvidersImgByProvider(provider.getProviderName())*/);
					imgProvider.setWidth("70px");
					providerPanel.add(imgProvider);
					providerPanel.add(WidgetFactory.createPricePanel(Double.toString(provider.getTotalCost())));
					grid.setWidget(row, col, providerPanel);
					count++;
				}
			}
			this.setVisible(true);
		}else{
			this.setVisible(false);
		}
	}
}
