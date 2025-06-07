package paymentservice

type PaymentModel struct {
	ID      int64 `json:"id"`
	Balance int64 `json:"balance"`
}

func NewPaymentModel(id int64, balance int64) *PaymentModel {
	return &PaymentModel{
		ID:      id,
		Balance: balance,
	}
}
