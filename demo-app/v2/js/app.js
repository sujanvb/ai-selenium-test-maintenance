const defaultCustomers = [
    {
        id: "001",
        name: "John Smith",
        email: "john@test.com",
        phone: "9876543210",
        address: "Bangalore",
        status: "Active"
    },
    {
        id: "002",
        name: "Jane Doe",
        email: "jane@test.com",
        phone: "9123456780",
        address: "Mumbai",
        status: "Active"
    },
    {
        id: "003",
        name: "Bob Wilson",
        email: "bob@test.com",
        phone: "9988776655",
        address: "Delhi",
        status: "Inactive"
    }
];


function initializeCustomers() {

    const existingCustomers =
        localStorage.getItem("customers");

    if (!existingCustomers) {

        localStorage.setItem(
            "customers",
            JSON.stringify(defaultCustomers)
        );
    }
}


function getCustomers() {

    initializeCustomers();

    return JSON.parse(
        localStorage.getItem("customers")
    );
}


function saveCustomers(customers) {

    localStorage.setItem(
        "customers",
        JSON.stringify(customers)
    );
}


function login() {

    window.location.href = "dashboard.html";
}


function openCustomers() {

    window.location.href = "customers.html";
}


function openCustomerDetails(customerId) {

    window.location.href =
        "customer-details.html?id=" + customerId;
}


function loadCustomerDetails() {

    const params =
        new URLSearchParams(
            window.location.search
        );

    const customerId =
        params.get("id");

    if (!customerId) {
        return;
    }

    const customers = getCustomers();

    const customer =
        customers.find(
            c => c.id === customerId
        );

    if (!customer) {
        return;
    }

    document.getElementById(
        "customer_name"
    ).value = customer.name;

    document.getElementById(
        "customer_email"
    ).value = customer.email;

    document.getElementById(
        "customer_phone"
    ).value = customer.phone;

    document.getElementById(
        "customer_address"
    ).value = customer.address;
}


function updateCustomer() {

    const params =
        new URLSearchParams(
            window.location.search
        );

    const customerId =
        params.get("id");

    if (!customerId) {
        return;
    }

    const customers = getCustomers();

    const customer =
        customers.find(
            c => c.id === customerId
        );

    if (!customer) {
        return;
    }

    customer.name =
        document.getElementById(
            "customer_name"
        ).value;

    customer.email =
        document.getElementById(
            "customer_email"
        ).value;

    customer.phone =
        document.getElementById(
            "customer_phone"
        ).value;

    customer.address =
        document.getElementById(
            "customer_address"
        ).value;

    saveCustomers(customers);

    window.location.href =
        "customer-details.html?id=" + customerId;
}


function goToCustomers() {

    window.location.href =
        "customers.html";
}


function loadCustomersTable() {

    const customers = getCustomers();

    customers.forEach(customer => {

        const nameElement =
            document.getElementById(
                "customerName" + customer.id
            );

        const emailElement =
            document.getElementById(
                "customerEmail" + customer.id
            );

        const statusElement =
            document.getElementById(
                "customerStatus" + customer.id
            );

        if (nameElement) {
            nameElement.textContent =
                customer.name;
        }

        if (emailElement) {
            emailElement.textContent =
                customer.email;
        }

        if (statusElement) {
            statusElement.textContent =
                customer.status;
        }

    });
}


document.addEventListener(
    "DOMContentLoaded",
    function () {

        if (
            window.location.pathname.endsWith(
                "customer-details.html"
            )
        ) {
            loadCustomerDetails();
        }

        if (
            window.location.pathname.endsWith(
                "customers.html"
            )
        ) {
            loadCustomersTable();
        }

        initializeCustomers();
    }
);