package ordersservice

type OrderModel struct {
	ID          int64  `json:"id"`
	UserID      int64  `json:"user_id"`
	Amount      int64  `json:"amount"`
	Description string `json:"description"`
	Status      string `json:"status"`
}
