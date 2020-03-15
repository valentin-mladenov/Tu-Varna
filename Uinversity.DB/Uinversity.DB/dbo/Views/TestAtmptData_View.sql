CREATE VIEW dbo.TestAtmptData_View
AS
SELECT        dbo.TestAttempt.Id AS TestAttemptId, dbo.TestAttempt.Date, dbo.Subject.Name AS Subject, dbo.Test.Id AS TestId, dbo.Test.Name AS Test, dbo.Question.Id AS QuestionId, dbo.Question.Data AS Question, 
                         dbo.Answer.Data AS Answer, dbo.Answer.IsCorrect, dbo.Subject.Semester AS TestSemester, { fn CONCAT(dbo.Teacher.FirstName + ' ', dbo.Teacher.LastName) } AS Teacher, scls.Name AS SubjectClass, 
                         dbo.Faculty.Name AS Faculty, { fn CONCAT(dbo.Student.FirstName + ' ', dbo.Student.LastName) } AS Student, dbo.Student.Email, dbo.Student.FacultyNumber, stcls.Name AS StudentClass
FROM            dbo.TestAttempt INNER JOIN
                         dbo.SelectedAnswer ON dbo.TestAttempt.Id = dbo.SelectedAnswer.TestAttemptId INNER JOIN
                         dbo.Answer ON dbo.SelectedAnswer.AnswerId = dbo.Answer.Id INNER JOIN
                         dbo.Question ON dbo.Question.Id = dbo.Answer.QuestionId INNER JOIN
                         dbo.Test ON dbo.Question.TestId = dbo.Test.Id INNER JOIN
                         dbo.Subject ON dbo.Test.SubjectId = dbo.Subject.Id INNER JOIN
                         dbo.Teacher ON dbo.Subject.TeacherId = dbo.Teacher.Id INNER JOIN
                         dbo.Faculty ON dbo.Teacher.FacultyId = dbo.Faculty.Id INNER JOIN
                         dbo.Class AS scls ON dbo.Subject.ClassId = scls.Id INNER JOIN
                         dbo.Student ON dbo.TestAttempt.StudentId = dbo.Student.Id INNER JOIN
                         dbo.Class AS stcls ON dbo.Student.ClassId = stcls.Id

GO
EXECUTE sp_addextendedproperty @name = N'MS_DiagramPane1', @value = N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[47] 4[14] 2[20] 3) )"
      End
      Begin PaneConfiguration = 1
         NumPanes = 3
         Configuration = "(H (1 [50] 4 [25] 3))"
      End
      Begin PaneConfiguration = 2
         NumPanes = 3
         Configuration = "(H (1 [50] 2 [25] 3))"
      End
      Begin PaneConfiguration = 3
         NumPanes = 3
         Configuration = "(H (4 [30] 2 [40] 3))"
      End
      Begin PaneConfiguration = 4
         NumPanes = 2
         Configuration = "(H (1 [56] 3))"
      End
      Begin PaneConfiguration = 5
         NumPanes = 2
         Configuration = "(H (2 [66] 3))"
      End
      Begin PaneConfiguration = 6
         NumPanes = 2
         Configuration = "(H (4 [50] 3))"
      End
      Begin PaneConfiguration = 7
         NumPanes = 1
         Configuration = "(V (3))"
      End
      Begin PaneConfiguration = 8
         NumPanes = 3
         Configuration = "(H (1[56] 4[18] 2) )"
      End
      Begin PaneConfiguration = 9
         NumPanes = 2
         Configuration = "(H (1 [75] 4))"
      End
      Begin PaneConfiguration = 10
         NumPanes = 2
         Configuration = "(H (1[66] 2) )"
      End
      Begin PaneConfiguration = 11
         NumPanes = 2
         Configuration = "(H (4 [60] 2))"
      End
      Begin PaneConfiguration = 12
         NumPanes = 1
         Configuration = "(H (1) )"
      End
      Begin PaneConfiguration = 13
         NumPanes = 1
         Configuration = "(V (4))"
      End
      Begin PaneConfiguration = 14
         NumPanes = 1
         Configuration = "(V (2))"
      End
      ActivePaneConfig = 0
   End
   Begin DiagramPane = 
      Begin Origin = 
         Top = 0
         Left = 0
      End
      Begin Tables = 
         Begin Table = "TestAttempt"
            Begin Extent = 
               Top = 27
               Left = 39
               Bottom = 157
               Right = 209
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "SelectedAnswer"
            Begin Extent = 
               Top = 26
               Left = 279
               Bottom = 137
               Right = 449
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "Answer"
            Begin Extent = 
               Top = 23
               Left = 489
               Bottom = 153
               Right = 659
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "Question"
            Begin Extent = 
               Top = 19
               Left = 706
               Bottom = 155
               Right = 875
            End
            DisplayFlags = 280
            TopColumn = 1
         End
         Begin Table = "Test"
            Begin Extent = 
               Top = 19
               Left = 912
               Bottom = 149
               Right = 1105
            End
            DisplayFlags = 280
            TopColumn = 1
         End
         Begin Table = "Subject"
            Begin Extent = 
               Top = 185
               Left = 707
               Bottom = 315
               Right = 877
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "Teacher"
            Begin Extent = 
               Top = 243
               Left = 932
               Bottom = 373
               Right = 1102
            End
            DisplayF', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'VIEW', @level1name = N'TestAtmptData_View';


GO
EXECUTE sp_addextendedproperty @name = N'MS_DiagramPane2', @value = N'lags = 280
            TopColumn = 0
         End
         Begin Table = "Faculty"
            Begin Extent = 
               Top = 341
               Left = 713
               Bottom = 441
               Right = 883
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "scls"
            Begin Extent = 
               Top = 349
               Left = 488
               Bottom = 445
               Right = 658
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "Student"
            Begin Extent = 
               Top = 191
               Left = 281
               Bottom = 317
               Right = 452
            End
            DisplayFlags = 280
            TopColumn = 2
         End
         Begin Table = "stcls"
            Begin Extent = 
               Top = 284
               Left = 64
               Bottom = 380
               Right = 234
            End
            DisplayFlags = 280
            TopColumn = 0
         End
      End
   End
   Begin SQLPane = 
   End
   Begin DataPane = 
      Begin ParameterDefaults = ""
      End
      Begin ColumnWidths = 15
         Width = 284
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
         Width = 1500
      End
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 2025
         Alias = 2355
         Table = 1170
         Output = 1275
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1155
         Or = 1350
         Or = 1350
      End
   End
End
', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'VIEW', @level1name = N'TestAtmptData_View';


GO
EXECUTE sp_addextendedproperty @name = N'MS_DiagramPaneCount', @value = 2, @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'VIEW', @level1name = N'TestAtmptData_View';

