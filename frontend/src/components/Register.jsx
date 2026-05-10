




import React, {

    useState

} from "react";

import API
from "../api/axiosConfig";

import {

    useNavigate

} from "react-router-dom";

function Register() {

    const navigate =
        useNavigate();

    const [user,
        setUser] = useState({

        name: "",

        email: "",

        password: ""
    });

    const handleChange = (e) => {

        setUser({

            ...user,

            [e.target.name]:
                e.target.value
        });
    };

    const handleSubmit =
        async (e) => {

        e.preventDefault();

        try {

            await API.post(

                "/auth/register",

                user
            );

            alert(
                "Registration Successful"
            );

            navigate("/login");

        } catch (error) {

            console.log(error);

            alert(
                "Registration Failed"
            );
        }
    };

    return (

        <div className="auth-container">

            <div className="auth-card">

                <h1>
                    Create Account
                </h1>

                <p>
                    Register your account
                </p>

                <form
                    onSubmit={handleSubmit}
                >

                    <input
                        type="text"
                        name="name"
                        placeholder="Enter Name"
                        onChange={handleChange}
                        required
                    />

                    <input
                        type="email"
                        name="email"
                        placeholder="Enter Email"
                        onChange={handleChange}
                        required
                    />

                    <input
                        type="password"
                        name="password"
                        placeholder="Enter Password"
                        onChange={handleChange}
                        required
                    />

                    <button type="submit">

                        Register

                    </button>

                </form>

            </div>

        </div>
    );
}

export default Register;