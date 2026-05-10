import React, {

    useEffect,

    useState

} from "react";

import API
from "../api/axiosConfig";

import {

    BarChart,
    Bar,
    XAxis,
    YAxis,
    Tooltip,
    CartesianGrid,

    PieChart,
    Pie,
    Cell,

    LineChart,
    Line,

    ResponsiveContainer

} from "recharts";

function AnalyticsCharts() {

    const [monthlyData,
        setMonthlyData] =
        useState([]);

    const [dayWiseData,
        setDayWiseData] =
        useState([]);

    const [categoryData,
        setCategoryData] =
        useState([]);

    useEffect(() => {

        fetchMonthly();

        fetchDayWise();

        fetchCategory();

    }, []);

    const fetchMonthly =
        async () => {

        try {

            const response =
                await API.get(

                    "/expenses/analytics/monthly"
                );

            setMonthlyData(
                response.data
            );

        } catch (error) {

            console.log(error);
        }
    };

    const fetchDayWise =
        async () => {

        try {

            const response =
                await API.get(

                    "/expenses/analytics/daywise"
                );

            setDayWiseData(
                response.data
            );

        } catch (error) {

            console.log(error);
        }
    };

    const fetchCategory =
        async () => {

        try {

            const response =
                await API.get(

                    "/expenses/analytics/category"
                );

            setCategoryData(
                response.data
            );

        } catch (error) {

            console.log(error);
        }
    };

    const COLORS = [
        "#8b5cf6",
        "#06b6d4",
        "#10b981",
        "#f59e0b",
        "#ef4444"
    ];

    return (

        <div className="charts-container">

            {/* MONTHLY */}

            <div className="chart-card">

                <h2>
                    Monthly Analytics
                </h2>

                <ResponsiveContainer
                    width="30%"
                    height={300}
                >

                    <BarChart
                        data={monthlyData}
                    >

                        <CartesianGrid
                            strokeDasharray="3 3"
                        />

                        <XAxis
                            dataKey="label"
                        />

                        <YAxis />

                        <Tooltip />

                        <Bar
                            dataKey="value"
                            fill="#8b5cf6"
                        />

                    </BarChart>

                </ResponsiveContainer>

            </div>

            {/* DAYWISE */}

            <div className="chart-card">

                <h2>
                    Day Wise Analytics
                </h2>

                <ResponsiveContainer
                    width="100%"
                    height={300}
                >

                    <LineChart
                        data={dayWiseData}
                    >

                        <CartesianGrid
                            strokeDasharray="3 3"
                        />

                        <XAxis
                            dataKey="label"
                        />

                        <YAxis />

                        <Tooltip />

                        <Line
                            type="monotone"
                            dataKey="value"
                            stroke="#06b6d4"
                        />

                    </LineChart>

                </ResponsiveContainer>

            </div>

            {/* CATEGORY */}

            <div className="chart-card">

                <h2>
                    Category Analytics
                </h2>

                <ResponsiveContainer
                    width="100%"
                    height={300}
                >

                    <PieChart>

                        {/* <Pie

                            data={categoryData}

                            dataKey="value"

                            nameKey="label"

                            outerRadius={100}

                            label
                        > */}
                        <Pie

    data={categoryData}

    dataKey="value"

    nameKey="label"

    cx="50%"

    cy="50%"

    innerRadius={70}

    outerRadius={120}

    label
>

                            {

                                categoryData.map(
                                    (
                                        entry,
                                        index
                                    ) => (

                                        <Cell

                                            key={index}

                                            fill={
                                                COLORS[
                                                index %
                                                COLORS.length
                                                ]
                                            }
                                        />
                                    )
                                )
                            }

                        </Pie>

                        <Tooltip />

                    </PieChart>

                </ResponsiveContainer>

            </div>

        </div>
    );
}

export default AnalyticsCharts;