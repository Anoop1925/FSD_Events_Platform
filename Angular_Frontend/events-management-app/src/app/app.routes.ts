import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { AboutComponent } from './pages/about/about.component';

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', redirectTo: '', pathMatch: 'full' },
  { path: 'about', component: AboutComponent },
  // { path: 'gallery', component: GalleryComponent },
  // { path: 'support', component: SupportComponent },
  // { path: 'auth/login', component: LoginComponent },
  // { path: 'auth/register', component: RegisterComponent },
  // { path: 'calendar', component: CalendarComponent },
  { path: '**', redirectTo: '' }
];
