import React, {

    useState

} from "react";

import { Navigate }
from "react-router-dom";

import API
from "../api/axiosConfig";

import ExpenseList
from "./ExpenseList";

import ExpenseChart
from "./ExpenseChart";

import AddExpense
from "./AddExpense";

import AnalyticsCharts
from "./AnalyticsCharts";

function Dashboard() {

    const token =
        localStorage.getItem("token");

    const [activeTab,
        setActiveTab] =
        useState("expenses");

    if (!token) {

        return <Navigate to="/" />;
    }

    const downloadPDF = async () => {

        try {

            const response =
                await API.get(

                    "/expenses/pdf",

                    {
                        responseType: "blob"
                    }
                );

            const url =
                window.URL.createObjectURL(

                    new Blob([response.data])
                );

            const link =
                document.createElement("a");

            link.href = url;

            link.setAttribute(
                "download",
                "expenses.pdf"
            );

            document.body.appendChild(link);

            link.click();

        } catch (error) {

            console.log(error);

            alert(
                "PDF Download Failed"
            );
        }
    };

    return (

        <div className="dashboard">

            {/* MENU */}

            


            <div className="menu-bar">

    <button
        onClick={() =>
            setActiveTab("add")
        }
    >
        Add Expense
    </button>

    <button
        onClick={() =>
            setActiveTab("expenses")
        }
    >
        Expense List
    </button>

    <button
        onClick={() =>
            setActiveTab("reports")
        }
    >
        Reports & Charts
    </button>

</div>

            {/* ADD EXPENSE */}

            {

                activeTab === "add" && (

                    <AddExpense />
                )
            }

            {/* EXPENSE LIST */}

            {

                activeTab === "expenses" && (

                    <>

                        <ExpenseList />

                        {/* <div className="section">

                            <button
                                className="pdf-btn"
                                onClick={downloadPDF}
                            >

                                Download PDF

                            </button>

                        </div> */}

                        <div className="section pdf-section">

    <h2>
        Download Expense Report
    </h2>

    <p className="pdf-text">

        Here you can download your
        complete expense report in PDF
        format including all your
        expenses, categories,
        monthly reports, and charts.

    </p>

    <button
        className="pdf-btn"
        onClick={downloadPDF}
    >

        Download PDF

    </button>

</div>

                    </>
                )
            }

            {/* REPORTS */}

            {

                activeTab === "reports" && (

                    <>

                        {/* <div className="dashboard-cards">

                            <div className="card">

                                <h3>
                                    Monthly Report
                                </h3>

                                <p>
                                    Rs. 24,500
                                </p>

                            </div>

                            <div className="card">

                                <h3>
                                    Total Expenses
                                </h3>

                                <p>
                                    Rs. 15,200
                                </p>

                            </div>

                            <div className="card">

                                <h3>
                                    Savings
                                </h3>

                                <p>
                                    Rs. 9,300
                                </p>

                            </div>

                        </div> */}

                        {/* <div className="section">

                            <ExpenseChart />

                        </div> */}
                        <div className="section">

    {/* OLD PIE CHART */}

    <ExpenseChart />

</div>

<div className="section">

    {/* NEW ANALYTICS */}

    <AnalyticsCharts />

</div>

                    </>
                )
            }

        </div>
    );
}

export default Dashboard;