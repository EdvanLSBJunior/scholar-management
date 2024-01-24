import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { ScholarsComponent } from './pages/scholars/scholars.component';

const routes: Routes = [
  { path: '', component: HomeComponent},
  { path: 'scholars', component: ScholarsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
