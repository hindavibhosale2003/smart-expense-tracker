import React, {

    useEffect,

    useState

} from "react";

import API from "../api/axiosConfig";

function ExpenseList() {

    const [expenses, setExpenses] =
            useState([]);

    useEffect(() => {

        fetchExpenses();

    }, []);

    const fetchExpenses = async () => {

        try {

            const response =
                    await API.get("/expenses");

            setExpenses(response.data);

        } catch (error) {

            console.log(error);
        }
    };

    const deleteExpense = async (id) => {

        try {

            await API.delete(

                `/expenses/${id}`
            );

            alert("Expense Deleted");

            fetchExpenses();

        } catch (error) {

            alert(error.response.data);
        }
    };

    return (

        <div className="container">

            <h2>Expense List</h2>

            <table>

                <thead>

                <tr>

                    <th>Title</th>

                    <th>Amount</th>

                    <th>Category</th>

                    <th>Date</th>

                    <th>Action</th>

                </tr>

                </thead>

                <tbody>

                {

                    expenses.map((expense) => (

                        <tr key={expense.id}>

                            <td>
                                {expense.title}
                            </td>

                            <td>
                                {expense.amount}
                            </td>

                            <td>
                                {expense.category}
                            </td>

                            <td>
                                {expense.date}
                            </td>

                            <td>

                                <button

                                    onClick={() =>
                                        deleteExpense(
                                            expense.id
                                        )
                                    }

                                    style={{
                                        backgroundColor:
                                                "red"
                                    }}
                                >
                                    Delete
                                </button>

                            </td>

                        </tr>
                    ))
                }

                </tbody>

            </table>

        </div>
    );
}

export default ExpenseList;