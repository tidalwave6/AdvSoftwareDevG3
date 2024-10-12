<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="css/styles.css">
    <title>Payment - Bookstore</title>
</head>
<body>
    <jsp:include page="nav-header.jsp" />

    <main>
        <section class="form-section animated">
            <h1 class="formTitle">Payment Methods</h1>
            <div class="payment-methods">
                <h2>Select a Payment Method</h2>
                <div class="stored-methods">
                    <div class="payment-card">
                        <label>
                            <input type="radio" name="paymentMethod" value="card1">
                            <span class="card-details">
                                Visa **** **** **** 1234 (Expiry: 09/25)
                            </span>
                        </label>
                    </div>
                    <div class="payment-card">
                        <label>
                            <input type="radio" name="paymentMethod" value="card2">
                            <span class="card-details">
                                MasterCard **** **** **** 5678 (Expiry: 12/24)
                            </span>
                        </label>
                    </div>
                    <div class="payment-card">
                        <label>
                            <input type="radio" name="paymentMethod" value="card3">
                            <span class="card-details">
                                Amex **** **** **** 9876 (Expiry: 07/23)
                            </span>
                        </label>
                    </div>
                </div>
                
                <button type="button" onclick="window.location.href='editPayment.jsp'" class="button">Edit</button>

                <button type="submit" formaction="checkout.jsp" class="button">Proceed to Checkout</button>
                
            </div>
        </section>
    </main><jsp:include page="footer.jsp"/>
</body>
</html>

