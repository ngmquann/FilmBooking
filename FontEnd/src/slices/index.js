import { combineReducers } from "redux";

// Front
import LayoutReducer from "./layouts/reducer";

// Authentication
import LoginReducer from "./auth/login/reducer";
import AccountReducer from "./auth/register/reducer";
import ForgetPasswordReducer from "./auth/forgetpwd/reducer";
import ProfileReducer from "./auth/profile/reducer";
import MessageReducer from "./message/reducer";

import CelebrityReducer from "./Celebrity/reducer";
import CinemaReducer from "./Cinemas/reducer";
import MovieGenreReducer from "./MovieGenre/reducer";
import MovieReducer from "./Movie/reducer";
import RoomReducer from "./Room/reducer";
import WaterCornReducer from "./WaterCorn/reducer";
import UserReducer from "./User/reducer";
import ShowTimeReducer from "./ShowTime/reducer";
import BlogReducer from "./Blog/reducer";
import ReviewReducer from "./Review/reducer";

//home
import HomeCelebrityReducer from "./home/CelebrityHome/reducer";
import HomeBlogOrReviewReducer from "./home/BlogAndReviewHome/reducer";
import HomeMovieReducer from "./home/MovieHome/reducer";

const rootReducer = combineReducers({
  Layout: LayoutReducer,
  Login: LoginReducer,
  Account: AccountReducer,
  ForgetPassword: ForgetPasswordReducer,
  Profile: ProfileReducer,
  Message: MessageReducer,
  Celebrity: CelebrityReducer,
  Cinema: CinemaReducer,
  MovieGenre: MovieGenreReducer,
  Movie: MovieReducer,
  RoomMovie: RoomReducer,
  WaterCorn: WaterCornReducer,
  User: UserReducer,
  ShowTime: ShowTimeReducer,
  Blog: BlogReducer,
  Review: ReviewReducer,
  HomeCelebrity: HomeCelebrityReducer,
  BlogOrReview: HomeBlogOrReviewReducer,
  HomeMovie: HomeMovieReducer,
});

export default rootReducer;
