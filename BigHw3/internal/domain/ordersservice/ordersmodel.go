package ordersservice

type OrdersModel struct {
	Orders []*OrderModel `json:"orders"`
}
