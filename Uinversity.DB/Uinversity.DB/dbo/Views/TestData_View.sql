CREATE VIEW dbo.TestData_View
AS
SELECT        dbo.Test.Name AS TestName, dbo.Test.QuestionCount, dbo.Test.QuestionVariations, dbo.Subject.Name AS SubjectName, dbo.Subject.Semester, { fn CONCAT(dbo.Teacher.FirstName + ' ', dbo.Teacher.LastName) 
                         } AS TeacherName, dbo.Faculty.Name AS FacultyName, dbo.Class.Name AS ClassName, dbo.Test.Id AS TestId, dbo.QuestionVariant.Variant, dbo.QuestionNumber.Number, dbo.Question.Data AS Question, 
                         dbo.Answer.Data AS Answer, dbo.Answer.IsCorrect
FROM            dbo.Test INNER JOIN
                         dbo.QuestionNumber ON dbo.Test.Id = dbo.QuestionNumber.TestId INNER JOIN
                         dbo.QuestionVariant ON dbo.QuestionNumber.Id = dbo.QuestionVariant.NumberId INNER JOIN
                         dbo.Question ON dbo.QuestionVariant.QuestionId = dbo.Question.Id INNER JOIN
                         dbo.Answer ON dbo.Answer.QuestionId = dbo.Question.Id INNER JOIN
                         dbo.Subject ON dbo.Test.SubjectId = dbo.Subject.Id INNER JOIN
                         dbo.Teacher ON dbo.Subject.TeacherId = dbo.Teacher.Id INNER JOIN
                         dbo.Faculty ON dbo.Teacher.FacultyId = dbo.Faculty.Id INNER JOIN
                         dbo.Class ON dbo.Subject.ClassId = dbo.Class.Id

GO
EXECUTE sp_addextendedproperty @name = N'MS_DiagramPane1', @value = N'[0E232FF0-B466-11cf-A24F-00AA00A3EFFF, 1.00]
Begin DesignProperties = 
   Begin PaneConfigurations = 
      Begin PaneConfiguration = 0
         NumPanes = 4
         Configuration = "(H (1[40] 4[20] 2[20] 3) )"
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
         Begin Table = "Test"
            Begin Extent = 
               Top = 180
               Left = 28
               Bottom = 310
               Right = 221
            End
            DisplayFlags = 280
            TopColumn = 1
         End
         Begin Table = "QuestionNumber"
            Begin Extent = 
               Top = 28
               Left = 279
               Bottom = 141
               Right = 449
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "QuestionVariant"
            Begin Extent = 
               Top = 31
               Left = 519
               Bottom = 144
               Right = 689
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "Question"
            Begin Extent = 
               Top = 34
               Left = 753
               Bottom = 164
               Right = 923
            End
            DisplayFlags = 280
            TopColumn = 1
         End
         Begin Table = "Answer"
            Begin Extent = 
               Top = 37
               Left = 987
               Bottom = 167
               Right = 1157
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "Subject"
            Begin Extent = 
               Top = 305
               Left = 284
               Bottom = 435
               Right = 454
            End
            DisplayFlags = 280
            TopColumn = 1
         End
         Begin Table = "Teacher"
            Begin Extent = 
               Top = 313
               Left = 524
               Bottom = 443
               Right = 694
            End
            Disp', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'VIEW', @level1name = N'TestData_View';


GO
EXECUTE sp_addextendedproperty @name = N'MS_DiagramPane2', @value = N'layFlags = 280
            TopColumn = 0
         End
         Begin Table = "Faculty"
            Begin Extent = 
               Top = 315
               Left = 747
               Bottom = 411
               Right = 917
            End
            DisplayFlags = 280
            TopColumn = 0
         End
         Begin Table = "Class"
            Begin Extent = 
               Top = 181
               Left = 524
               Bottom = 277
               Right = 694
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
   End
   Begin CriteriaPane = 
      Begin ColumnWidths = 11
         Column = 1440
         Alias = 900
         Table = 1170
         Output = 720
         Append = 1400
         NewValue = 1170
         SortType = 1350
         SortOrder = 1410
         GroupBy = 1350
         Filter = 1350
         Or = 1350
         Or = 1350
         Or = 1350
      End
   End
End
', @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'VIEW', @level1name = N'TestData_View';


GO
EXECUTE sp_addextendedproperty @name = N'MS_DiagramPaneCount', @value = 2, @level0type = N'SCHEMA', @level0name = N'dbo', @level1type = N'VIEW', @level1name = N'TestData_View';

