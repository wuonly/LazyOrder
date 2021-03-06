package com.cisoft.shop.finishorder.presenter;

import android.content.Context;

import com.cisoft.shop.Api;
import com.cisoft.shop.bean.Order;
import com.cisoft.shop.finishorder.view.IOrderView;
import com.cisoft.shop.goods.model.INetWorkFinished;
import com.cisoft.shop.goods.model.ShopModel;
import com.cisoft.shop.order.model.IOrderModel;
import com.cisoft.shop.order.model.OrderModel;

import org.kymjs.aframe.ui.ViewInject;

import java.util.List;

/**
 * Created by Lecion on 12/4/14.
 */
public class OrderPresenter {
    IOrderView view;
    IOrderModel model;
    ShopModel shopModel;

    public OrderPresenter(Context context, IOrderView view) {
        this.view = view;
        model = new OrderModel(context);
        shopModel = new ShopModel(context);
    }

    public void onLoad() {
        final int size = 5;
        view.setPage(1);
        view.showProgress();
        model.findOrdersByMerId(Api.ORDER_STATE_READY, 1, size, new INetWorkFinished<Order>() {
            @Override
            public void onSuccess(List<Order> l) {
                if (l.size() == 0 || l.size() < size) {
                    view.setPullLoadEnable(false);
                } else {
                    view.setPullLoadEnable(true);
                }
                view.setOrderList(l);
                view.hideProgress();
            }

            @Override
            public void onFailure(String info) {
                view.showNoData();
            }
        });
    }

    public void loadMore(int page, final int size) {
        model.findOrdersByMerId(Api.ORDER_STATE_READY, page, size, new INetWorkFinished<Order>() {
            @Override
            public void onSuccess(List<Order> l) {
                if (l.size() == 0 || l.size() < size) {
                    view.setPullLoadEnable(false);
                    ViewInject.toast("已经加载完了~");
                } else {
                    view.setOrderList(l);
                }
                view.hideMoreProgress();
                view.setOnLoadMore(false);
            }

            @Override
            public void onFailure(String info) {
                ViewInject.toast(info);
                view.hideMoreProgress();
                view.setOnLoadMore(false);
            }
        });
    }

    /**
     * 切换商店营业状态
     * @param oldState 切换之前的状态
     * @param newState 切换之后的状态
     */
    public void switchShopState(final int oldState, final int newState) {
        if (oldState == newState) {
            return;
        }
        shopModel.updateOperateState(newState, new ShopModel.IUpdateOperateState() {
            @Override
            public void onSuccess(int code) {
                view.setOperatingState(newState);
            }

            @Override
            public void onFailure(String msg) {
                view.setOperatingState(oldState);
                ViewInject.toast(msg);
            }
        });
    }

    /**
     * 切换订单状态
     * @param orderId
     * @param position
     * @param state
     */
    public void switchOrderStatus(int orderId, final int position, final String state) {

        model.updateOrderState(orderId, state, new OrderModel.IUpdateOrderState() {
            @Override
            public void onSuccess(int code) {
                view.setOrderStatus(position, state);
            }

            @Override
            public void onFailure(String msg) {
                ViewInject.toast(msg);
            }
        });
    }

}
