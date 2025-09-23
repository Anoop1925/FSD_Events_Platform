import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { AboutComponent } from './pages/about/about.component';
import { GalleryComponent } from './pages/gallery/gallery.component';
import { SupportComponent } from './pages/support/support.component';
import { SigninComponent } from './pages/auth/signin/signin.component';
import { StudentLoginComponent } from './pages/auth/student-login/student-login.component';
import { AdminLoginComponent } from './pages/auth/admin-login/admin-login.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', redirectTo: '', pathMatch: 'full' },
  { path: 'about', component: AboutComponent },
  { path: 'gallery', component: GalleryComponent },
  { path: 'support', component: SupportComponent },
  { path: 'auth/signin', component: SigninComponent },
  { path: 'auth/signup', redirectTo: 'auth/student-login', pathMatch: 'full' },
  { path: 'auth/student-login', component: StudentLoginComponent },
  { path: 'auth/admin-login', component: AdminLoginComponent },
  { path: 'auth/student-signup', redirectTo: 'auth/student-login', pathMatch: 'full' },
  { path: 'auth/login', redirectTo: 'auth/signin', pathMatch: 'full' },
  { path: 'auth/register', redirectTo: 'auth/student-login', pathMatch: 'full' },
  // { path: 'calendar', component: CalendarComponent },
  { path: '**', redirectTo: '' }
];
