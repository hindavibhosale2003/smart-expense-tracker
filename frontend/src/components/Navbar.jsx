



import React, {
    useState
} from "react";

import {
    Link
} from "react-router-dom";

function Navbar() {

    const token =
        localStorage.getItem("token");

    const email =
        localStorage.getItem("email");

    const username =
        localStorage.getItem("username");

    const [showProfile,
        setShowProfile] =
        useState(false);

    const logout = () => {

        localStorage.removeItem("token");

        localStorage.removeItem("email");

        localStorage.removeItem("username");

        window.location.href = "/";
    };

    return (

        <div className="navbar">

            {/* LOGO */}

            <h2 className="logo">

                Expense Tracker

            </h2>

            {/* NAVIGATION */}

            <div className="nav-links">

                {/* HOME BUTTON */}

                <Link to="/">

                    Home

                </Link>

                {

                    !token ? (

                        <>

                            {/* LOGIN */}

                            <Link to="/login">

                                Login

                            </Link>

                            {/* REGISTER */}

                            <Link to="/register">

                                Register

                            </Link>

                        </>

                    ) : (

                        <>

                            {/* DASHBOARD */}

                            <Link to="/dashboard">

                                Dashboard

                            </Link>

                            {/* PROFILE BUTTON */}

                            <button

                                className="profile-btn"

                                onClick={() =>
                                    setShowProfile(
                                        !showProfile
                                    )
                                }
                            >

                                👤 Profile

                            </button>

                            {/* PROFILE CARD */}


                            {
                                showProfile && (

                                    <div className="profile-card">

                                        <div className="profile-top">

                                            <div className="profile-avatar">

                                                👤

                                            </div>

                                            <div>

                                                <h3>
                                                    {username}
                                                </h3>

                                                <p>
                                                    {email}
                                                </p>

                                            </div>

                                        </div>

                                        <button
                                            className="logout-btn"
                                            onClick={logout}
                                        >

                                            Logout

                                        </button>

                                    </div>
                                )
                            }

                        </>
                    )
                }

            </div>

        </div>
    );
}

export default Navbar;