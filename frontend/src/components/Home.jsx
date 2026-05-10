import React from "react";

import { Link }
from "react-router-dom";

/* IMPORT IMAGE */

import financeImage
from "../assets/finance.png";

function Home() {

    return (

        <div className="home">

            <div className="hero">

                {/* LEFT SIDE */}

                <div className="hero-left">

                    <h1>

                        Expense Tracking,
                        <span>
                            Simplified
                        </span>

                    </h1>

                    <p>

                        Track your daily expenses,
                        manage reports,
                        charts,
                        and PDF exports.

                    </p>

                    <div className="hero-buttons">

                        <Link to="/login">

                            <button>

                                Login

                            </button>

                        </Link>

                        <Link to="/register">

                            <button
                                className="register-btn"
                            >

                                Register

                            </button>

                        </Link>

                    </div>

                </div>

                {/* RIGHT SIDE IMAGE */}

                <div className="hero-right">

                    <img

                        src={financeImage}

                        alt="Finance"

                        className="hero-image"
                    />

                </div>

            </div>

        </div>
    );
}

export default Home;