import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminComponent } from './modules/admin/admin.component';
import { ErrorPageComponent } from './modules/component/error-page/error-page.component';
import { HolidayPageComponent } from './modules/component/holiday-page/holiday-page.component';
import { HolidayPlanPageComponent } from './modules/component/holiday-plan-page/holiday-plan-page.component';
import { UserPageComponent } from './modules/component/user-page/user-page.component';
import { WelcomeComponent } from './modules/component/welcome/welcome.component';


const routes: Routes = [
  { path: "", component: WelcomeComponent },
  { path: "admin", component: AdminComponent },
  { path: "users", component: UserPageComponent },
  { path: "holidays", component: HolidayPageComponent },
  { path: "holidays-plan", component: HolidayPlanPageComponent },
  { path: "error-page", component: ErrorPageComponent },
  { path: "**", redirectTo: "error-page", pathMatch: "full" },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
