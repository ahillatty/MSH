package ui.viewcontroller.staff;

import blservice.orderblservice.OrderBLService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import main.Main;
import ui.componentcontroller.order.ClientOrderPagePaneController;
import ui.componentcontroller.order.HotelOrderCellController;
import ui.componentcontroller.order.HotelOrderPagePaneController;
import ui.componentcontroller.order.HotelOrderSearchPaneController;
import util.OrderState;
import vo.OrderVO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sorumi on 16/12/3.
 */
public class HotelOrderListViewController {

    private static final int NUM_OF_CELL = 4;

    private HotelOrderViewController hotelOrderViewController;

    @FXML
    private VBox contentVBox;

    private FXMLLoader[] cellLoaders = new FXMLLoader[NUM_OF_CELL];
    private Node[] cells = new Node[NUM_OF_CELL];

    private Node pagePane;
    private HotelOrderPagePaneController hotelOrderPagePaneController;

    private OrderBLService orderBLService;

    private ArrayList<OrderVO> orders = new ArrayList<>();

    /**
     * Initializes the ClientOrderListViewController class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    public void initialize() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../component/order/HotelOrderSearchPane.fxml"));
            VBox pane = loader.load();

            HotelOrderSearchPaneController hotelOrderSearchPaneController = loader.getController();
            hotelOrderSearchPaneController.setHotelOrderListViewController(this);

            contentVBox.getChildren().add(pane);

            FXMLLoader pageLoader = new FXMLLoader();
            pageLoader.setLocation(Main.class.getResource("../component/order/HotelOrderPagePane.fxml"));
            pagePane = pageLoader.load();

            hotelOrderPagePaneController = pageLoader.getController();
            hotelOrderPagePaneController.setHotelOrderListViewController(this);

            for (int i = 0; i < NUM_OF_CELL; i++) {
                FXMLLoader cellLoader = new FXMLLoader();
                cellLoader.setLocation(Main.class.getResource("../component/order/HotelOrderCell.fxml"));
                HBox ordercell = cellLoader.load();

                cellLoaders[i] = cellLoader;
                cells[i] = ordercell;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setHotelOrderViewController(HotelOrderViewController hotelOrderViewController) {
        this.hotelOrderViewController = hotelOrderViewController;
    }

    public void setOrderBLService(OrderBLService orderBLService) {
        this.orderBLService = orderBLService;
    }

    public void showOrders(OrderState orderState) {
        //TODO
        orders = orderBLService.searchHotelOrder("01011234", orderState, null);
        int size = orders.size();
        hotelOrderPagePaneController.setPageCount(size/NUM_OF_CELL + ((size%NUM_OF_CELL == 0) ? 0 : 1));
        if (size > 0) {
            turnPage(1);
        } else {
            System.out.println("No Order");
        }

    }

    public void turnPage(int page) {
        int fromIndex = (page-1)*NUM_OF_CELL;
        int toIndex = Math.min(page*NUM_OF_CELL, orders.size());
        List<OrderVO> tmpOrders = orders.subList(fromIndex, toIndex);
        setCells(tmpOrders);
    }

    private void setCells(List<OrderVO> orders) {

        if (orders.size() > NUM_OF_CELL) {
            System.out.println("ERROR");
            return;
        }

        for (Node cell : cells) {
            contentVBox.getChildren().remove(cell);
        }

        contentVBox.getChildren().remove(pagePane);

        for (int i = 0; i < orders.size(); i++) {

            OrderVO order = orders.get(i);
            FXMLLoader loader = cellLoaders[i];
            Node ordercell = cells[i];

            HotelOrderCellController hotelOrderCellController = loader.getController();
            hotelOrderCellController.setHotelOrderListViewController(this);
            hotelOrderCellController.setOrder(order);

            contentVBox.getChildren().add(ordercell);
        }

        contentVBox.getChildren().add(pagePane);
    }

    public void showHotelOrderDetail(OrderVO order) {
        hotelOrderViewController.showHotelOrderDetail(order);
    }
}