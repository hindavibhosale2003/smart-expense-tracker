import React, { useState } from "react";

import API from "../api/axiosConfig";

function AddExpense() {

    const [expense, setExpense] = useState({

        title: "",

        amount: "",

        category: "",

        date: "",

        description: ""
    });

    const handleChange = (e) => {

        setExpense({

            ...expense,

            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {

        e.preventDefault();

        try {

            await API.post(

                "/expenses",

                expense
            );

            alert("Expense Added");

            window.location.reload();

        } catch (error) {

            alert(error.response.data);
        }
    };

    return (

    <div className="add-expense-card">

        <h2>
            Add Expense
        </h2>

        <form
            className="expense-form"
            onSubmit={handleSubmit}
        >

            <input
                type="text"
                name="title"
                placeholder="Expense Title"
                onChange={handleChange}
                required
            />

            <input
                type="number"
                name="amount"
                placeholder="Amount"
                onChange={handleChange}
                required
            />

            <input
                type="text"
                name="category"
                placeholder="Category"
                onChange={handleChange}
                required
            />

            <input
                type="date"
                name="date"
                onChange={handleChange}
                required
            />

            <textarea
                name="description"
                placeholder="Description"
                onChange={handleChange}
            />

            <button type="submit">

                Add Expense

            </button>

        </form>

    </div>
);
}

export default AddExpense;