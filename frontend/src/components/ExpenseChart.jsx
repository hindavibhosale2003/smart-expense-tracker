import React, {

    useEffect,
    useState

} from "react";

import API from "../api/axiosConfig";

import {

    PieChart,
    Pie,
    Cell,
    Tooltip,
    Legend

} from "recharts";

function ExpenseChart() {

    const [chartData,
        setChartData] = useState([]);

    useEffect(() => {

        fetchChartData();

    }, []);

    const fetchChartData =
        async () => {

        try {

            const response =
                await API.get(
                    "/expenses/chart"
                );

            console.log(
                response.data
            );

            setChartData(
                response.data
            );

        } catch (error) {

            console.log(error);
        }
    };

    const COLORS = [

        "#8884d8",
        "#82ca9d",
        "#ffc658",
        "#ff8042",
        "#0088FE"
    ];

    return (

    <div className="chart-card">

        <h2 className="chart-title">

            Expense Chart

        </h2>

        {

            chartData.length > 0 ? (

                <div className="pie-wrapper">

                    <PieChart
                        width={400}
                        height={300}
                    >

                        <Pie

                        data={chartData}

                         dataKey="totalAmount"

                        nameKey="category"

                            cx="50%"

                            cy="50%"

                            outerRadius={100}

                            label
                        >

                            {

                                chartData.map(
                                    (entry, index) => (

                                    <Cell

                                        key={`cell-${index}`}

                                        fill={
                                            COLORS[
                                            index %
                                            COLORS.length
                                            ]
                                        }
                                    />
                                ))
                            }

                        </Pie>


                        <Tooltip />

                        <Legend />

                    </PieChart>

                </div>

            ) : (

                <p>
                    No Chart Data Found
                </p>
            )
        }

    </div>
);
}

export default ExpenseChart;