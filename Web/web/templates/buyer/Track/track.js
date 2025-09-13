function trackOrder() {
    const input = document.getElementById("orderInput").value;
    const info = document.getElementById("orderInfo");

    if(input.trim() !== "") {
        info.classList.remove("hidden");

        const steps = document.querySelectorAll(".step");
        steps.forEach(step => step.classList.remove("active"));

        const statusText = document.getElementById("statusText");

        // Example: tum yahan order ka status define kar rahi ho
        // Real app mein ye server se aayega
        const orderStatus = "dispatched"; // options: "placed", "dispatched", "delivered"

        if(orderStatus === "placed") {
            statusText.textContent = "Your order is placed and being processed.";
            steps[0].classList.add("active");

        } else if(orderStatus === "dispatched") {
            statusText.textContent = "Your order has been dispatched.";
            setTimeout(() => steps[0].classList.add("active"), 500);
            setTimeout(() => steps[1].classList.add("active"), 1500);

        } else if(orderStatus === "delivered") {
            statusText.textContent = "Your order has been delivered.";
            setTimeout(() => steps[0].classList.add("active"), 500);
            setTimeout(() => steps[1].classList.add("active"), 1500);
            setTimeout(() => steps[2].classList.add("active"), 2500);
        }
    } else {
        alert("Please enter Order ID or Phone Number");
    }
}