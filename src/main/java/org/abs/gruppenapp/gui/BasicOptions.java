package org.abs.gruppenapp.gui;

import javax.swing.JFrame;
import lombok.AllArgsConstructor;
import org.abs.gruppenapp.repository.TeacherRepository;
import org.abs.gruppenapp.services.DatabaseService;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BasicOptions extends JFrame {

  private final DatabaseService databaseService;


  public void logout(){
    Login login = new Login(databaseService);
    login.setVisible(true);
    login.initialize();
    setVisible(false);
  }

  public void backToAdminDashboard(){
    DashboardAdmin dashboardAdmin = new DashboardAdmin(databaseService);
    dashboardAdmin.setVisible(true);
    dashboardAdmin.initialize();
    setVisible(false);
  }

  public void backToTeacherDashboard(){
    DashboardTeacher dashboardTeacher = new DashboardTeacher(databaseService);
    dashboardTeacher.setVisible(true);
    dashboardTeacher.initialize();
    setVisible(false);
  }
}
