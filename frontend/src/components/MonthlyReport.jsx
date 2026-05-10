
import React, {

    useState

} from "react";

import API from "../api/axiosConfig";

function MonthlyReport() {

    const [month, setMonth] =
            useState(1);

    const [total, setTotal] =
            useState(0);

    const getReport = async () => {

        try {

            const response =
                    await API.get(

                `/expenses/monthly-report?month=${month}`
            );

            setTotal(response.data);

        } catch (error) {

            alert(error.response.data);
        }
    };

    return (

        <div className="container">

            <h2>Monthly Report</h2>

            <input

                type="number"

                value={month}

                onChange={(e) =>
                        setMonth(e.target.value)
                }
            />

            <button onClick={getReport}>

                Get Report

            </button>

            <h3>

                Total Expense :
                ₹ {total}

            </h3>

        </div>
    );
}

export default MonthlyReport;