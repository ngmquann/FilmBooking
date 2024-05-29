import React, { Suspense } from "react";
import { Routes, Route } from "react-router-dom";

import { PuffLoader } from "react-spinners";
//Layouts
import NonAuthLayout from "../Layouts/NonAuthLayout";
import VerticalLayout from "../Layouts/index";
import LayoutHome from "../Layouts/home/index";

//routes
import {
  authProtectedRoutes,
  publicRoutes,
  homeRoutes,
  CinemaCornerRoutes,
  TicketBookingRoutes,
  BinhLuanPhimRoutes,
} from "./allRoutes";
import { AuthProtected } from "./AuthProtected";

const loading = (
  <div
    style={{
      display: "flex",
      justifyContent: "center",
      alignItems: "center",
      height: "100vh",
    }}
  >
    <PuffLoader color={"#c6ec0c"} loading={true} size={76} />
  </div>
);

const Index = () => {
  return (
    <React.Fragment>
      <Suspense fallback={loading}>
        <Routes>
          <Route>
            {publicRoutes.map((route, idx) => (
              <Route
                path={route.path}
                element={
                  <NonAuthLayout>
                    {route.element ? route.element : route.component}
                  </NonAuthLayout>
                }
                key={idx}
                exact={true}
              />
            ))}
          </Route>
          {/* home router */}
          <Route>
            {homeRoutes.map((route, idx) => (
              <Route
                path={route.path}
                element={<LayoutHome>{route.component}</LayoutHome>}
                key={idx}
                exact={true}
              />
            ))}
          </Route>

          {/* CinemaCorner */}
          {CinemaCornerRoutes.map((route, idx) => (
            <Route
              path={route.path}
              element={<LayoutHome>{route.component}</LayoutHome>}
              key={idx}
              exact={true}
            />
          ))}
          {/* Ticket Booking */}
          {TicketBookingRoutes.map((route, idx) => (
            <Route
              path={route.path}
              element={<LayoutHome>{route.component}</LayoutHome>}
              key={idx}
              exact={true}
            />
          ))}

          {/* Binh luận phim */}
          {BinhLuanPhimRoutes.map((route, idx) => (
            <Route
              path={route.path}
              element={<LayoutHome>{route.component}</LayoutHome>}
              key={idx}
              exact={true}
            />
          ))}


          <Route>
            {authProtectedRoutes.map((route, idx) => (
              <Route
                path={route.path}
                element={
                  <AuthProtected>
                    <VerticalLayout>
                      {route.element ? route.element : route.component}
                    </VerticalLayout>
                  </AuthProtected>
                }
                key={idx}
                exact={true}
              />
            ))}
          </Route>
        </Routes>
      </Suspense>
    </React.Fragment>
  );
};

export default Index;
