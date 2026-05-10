

import React, {
    useState
} from "react";

import API from "../api/axiosConfig";

import { useNavigate }
from "react-router-dom";

function Login() {

    const navigate =
        useNavigate();

    const [loginData,
        setLoginData] = useState({

        email: "",

        password: ""
    });

    const handleChange = (e) => {

        setLoginData({

            ...loginData,

            [e.target.name]:
                e.target.value
        });
    };

    const handleSubmit =
        async (e) => {

        e.preventDefault();

        try {

            const response =
                await API.post(

                    "/auth/login",

                    loginData
                );

            // Save Token
            localStorage.setItem(
                "token",
                response.data
            );

            // Save Email
            localStorage.setItem(
                "email",
                loginData.email
            );

            // Save Username
            localStorage.setItem(
                "username",
                loginData.email
            );

            // Navigate Dashboard
            navigate("/dashboard");

            // Reload Navbar
            window.location.reload();

        } catch (error) {

            alert(
                "Invalid Credentials"
            );
        }
    };

    return (

        <div className="auth-container">

            <div className="auth-card">

                <h1>
                    Welcome Back
                </h1>

                <p>
                    Login to continue
                </p>

                <form onSubmit={handleSubmit}>

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

                        Login

                    </button>

                </form>

            </div>

        </div>
    );
}

export default Login;