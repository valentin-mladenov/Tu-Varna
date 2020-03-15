
USE [University.DB]
GO

INSERT [dbo].[Class] ([Id], [Name]) VALUES (N'345f8b19-86d2-4615-b41e-13957e1bb77f', N'Клас 3')
GO

INSERT [dbo].[Class] ([Id], [Name]) VALUES (N'54ea8865-5b32-4133-aff5-268cbff6a95a', N'Класе 4')
GO

INSERT [dbo].[Class] ([Id], [Name]) VALUES (N'90fa74a9-d876-491b-8e6f-7d2ec9c75a48', N'Клас 1')
GO

INSERT [dbo].[Class] ([Id], [Name]) VALUES (N'd74ddc9c-8d7e-4410-b2e1-c7ac13ef758c', N'Клас 5')
GO

INSERT [dbo].[Class] ([Id], [Name]) VALUES (N'0f48f35f-53d5-4363-b4fc-e4ac94035d0c', N'Клас 2')
GO

INSERT [dbo].[Faculty] ([Id], [Name]) VALUES (N'985dc490-4228-4f5d-a28f-0790d88625cf', N'Факултет 5')
GO

INSERT [dbo].[Faculty] ([Id], [Name]) VALUES (N'00e562e0-d623-4935-b46e-084fe30523b6', N'Факултет 1')
GO

INSERT [dbo].[Faculty] ([Id], [Name]) VALUES (N'd710782b-0d98-4748-a9f4-10064180c65a', N'Факултет 2')
GO

INSERT [dbo].[Faculty] ([Id], [Name]) VALUES (N'79690b15-52b5-4094-be99-78a500479c12', N'Факултет 3')
GO

INSERT [dbo].[Faculty] ([Id], [Name]) VALUES (N'5afcc233-9f8b-438f-a1b0-f49e6940b5b1', N'Факултет 4')
GO

INSERT [dbo].[Teacher] ([Id], [FirstName], [LastName], [FacultyId]) VALUES (N'405a68fc-1962-4b74-9fec-3a25753eda85', N'Георги', N'Тошев', N'd710782b-0d98-4748-a9f4-10064180c65a')
GO

INSERT [dbo].[Teacher] ([Id], [FirstName], [LastName], [FacultyId]) VALUES (N'8d579e4f-7daf-46c9-b847-4c80e6ff4629', N'Петър', N'Петров', N'00e562e0-d623-4935-b46e-084fe30523b6')
GO

INSERT [dbo].[Teacher] ([Id], [FirstName], [LastName], [FacultyId]) VALUES (N'78d24a1e-3772-4af5-bbb4-bc0ea1c45102', N'Христо', N'Динев', N'd710782b-0d98-4748-a9f4-10064180c65a')
GO

INSERT [dbo].[Teacher] ([Id], [FirstName], [LastName], [FacultyId]) VALUES (N'59275825-bdb2-4a08-a7de-bd6f27e7a53e', N'Иван', N'Иванов', N'00e562e0-d623-4935-b46e-084fe30523b6')
GO

INSERT [dbo].[Teacher] ([Id], [FirstName], [LastName], [FacultyId]) VALUES (N'5368c146-ca2b-4cd3-a28b-d2403f6b4443', N'Лазар', N'Димитров', N'd710782b-0d98-4748-a9f4-10064180c65a')
GO

INSERT [dbo].[Subject] ([Id], [Name], [Semester], [ClassId], [TeacherId]) VALUES (N'926a2afb-e78c-4461-a799-15047a28ebcf', N'Дисциплина 1', 3, N'0f48f35f-53d5-4363-b4fc-e4ac94035d0c', N'59275825-bdb2-4a08-a7de-bd6f27e7a53e')
GO

INSERT [dbo].[Subject] ([Id], [Name], [Semester], [ClassId], [TeacherId]) VALUES (N'86cde8c6-6a56-46a4-b6bc-5d04e6f250f1', N'Дисциплина 2', 1, N'90fa74a9-d876-491b-8e6f-7d2ec9c75a48', N'8d579e4f-7daf-46c9-b847-4c80e6ff4629')
GO

INSERT [dbo].[Subject] ([Id], [Name], [Semester], [ClassId], [TeacherId]) VALUES (N'34eee25a-5609-4dbe-956f-63b9dbc9869e', N'Дисциплина 3', 4, N'0f48f35f-53d5-4363-b4fc-e4ac94035d0c', N'78d24a1e-3772-4af5-bbb4-bc0ea1c45102')
GO

INSERT [dbo].[Subject] ([Id], [Name], [Semester], [ClassId], [TeacherId]) VALUES (N'9ce33e32-0980-493b-8ba1-cd708dee6f31', N'Дисциплина 4', 1, N'90fa74a9-d876-491b-8e6f-7d2ec9c75a48', N'405a68fc-1962-4b74-9fec-3a25753eda85')
GO

INSERT [dbo].[Subject] ([Id], [Name], [Semester], [ClassId], [TeacherId]) VALUES (N'd12bd4ee-6188-4586-a4e1-e968fe3b5dfa', N'Дисциплина 5', 2, N'90fa74a9-d876-491b-8e6f-7d2ec9c75a48', N'5368c146-ca2b-4cd3-a28b-d2403f6b4443')
GO

INSERT [dbo].[Test] ([Id], [Name], [SubjectId], [QuestionCount], [QuestionVariations]) VALUES (N'3d685919-2d61-423e-9de5-043346e06ab3', N'Тест 5', N'd12bd4ee-6188-4586-a4e1-e968fe3b5dfa', 3, 3)
GO

INSERT [dbo].[Test] ([Id], [Name], [SubjectId], [QuestionCount], [QuestionVariations]) VALUES (N'45eb8782-46bb-4bde-a2a5-2817b9ce4ac3', N'Тест 1', N'9ce33e32-0980-493b-8ba1-cd708dee6f31', 3, 3)
GO

INSERT [dbo].[Test] ([Id], [Name], [SubjectId], [QuestionCount], [QuestionVariations]) VALUES (N'4d5e42ff-cbb2-415d-9b40-423d99a81150', N'Тест 2', N'86cde8c6-6a56-46a4-b6bc-5d04e6f250f1', 3, 3)
GO

INSERT [dbo].[Test] ([Id], [Name], [SubjectId], [QuestionCount], [QuestionVariations]) VALUES (N'32636d29-fb1d-48b9-b682-a349df8c5989', N'Тест 3', N'926a2afb-e78c-4461-a799-15047a28ebcf', 3, 3)
GO

INSERT [dbo].[Test] ([Id], [Name], [SubjectId], [QuestionCount], [QuestionVariations]) VALUES (N'95bc9f1b-d73a-4091-a5a9-d3b41c9bd30f', N'Тест 4', N'34eee25a-5609-4dbe-956f-63b9dbc9869e', 3, 3)
GO

INSERT [dbo].[QuestionNumber] ([Id], [Number], [TestId]) VALUES (N'5fef7438-98cc-4479-a0a7-166cf073965c', 1, N'3d685919-2d61-423e-9de5-043346e06ab3')
GO

INSERT [dbo].[QuestionNumber] ([Id], [Number], [TestId]) VALUES (N'b5d5139f-c955-48b2-84c0-240d6d3a3cd7', 3, N'45eb8782-46bb-4bde-a2a5-2817b9ce4ac3')
GO

INSERT [dbo].[QuestionNumber] ([Id], [Number], [TestId]) VALUES (N'758f047d-55af-4da5-acb2-2a03ffff8049', 1, N'32636d29-fb1d-48b9-b682-a349df8c5989')
GO

INSERT [dbo].[QuestionNumber] ([Id], [Number], [TestId]) VALUES (N'58c04818-e0ab-41d7-9f7d-2c21c8b4e4fd', 2, N'45eb8782-46bb-4bde-a2a5-2817b9ce4ac3')
GO

INSERT [dbo].[QuestionNumber] ([Id], [Number], [TestId]) VALUES (N'97e036a6-6fb5-46bf-b957-333143f9d83d', 3, N'95bc9f1b-d73a-4091-a5a9-d3b41c9bd30f')
GO

INSERT [dbo].[QuestionNumber] ([Id], [Number], [TestId]) VALUES (N'8a7a4341-e9d9-4d6c-bc2c-3822239666af', 1, N'95bc9f1b-d73a-4091-a5a9-d3b41c9bd30f')
GO

INSERT [dbo].[QuestionNumber] ([Id], [Number], [TestId]) VALUES (N'345cbfd3-10c3-40cc-a01f-43595eb908ad', 3, N'32636d29-fb1d-48b9-b682-a349df8c5989')
GO

INSERT [dbo].[QuestionNumber] ([Id], [Number], [TestId]) VALUES (N'23121f0b-d446-4af7-a0ed-4739988a349d', 1, N'4d5e42ff-cbb2-415d-9b40-423d99a81150')
GO

INSERT [dbo].[QuestionNumber] ([Id], [Number], [TestId]) VALUES (N'0822e373-6ea9-4bbb-a636-6ff8a7a74957', 2, N'4d5e42ff-cbb2-415d-9b40-423d99a81150')
GO

INSERT [dbo].[QuestionNumber] ([Id], [Number], [TestId]) VALUES (N'0f7e1a70-8f45-4ea4-931e-7236b8aa2965', 2, N'3d685919-2d61-423e-9de5-043346e06ab3')
GO

INSERT [dbo].[QuestionNumber] ([Id], [Number], [TestId]) VALUES (N'3557ae09-aa95-4716-9c36-7c3d328b9099', 3, N'3d685919-2d61-423e-9de5-043346e06ab3')
GO

INSERT [dbo].[QuestionNumber] ([Id], [Number], [TestId]) VALUES (N'da511061-dbf4-434e-8963-b59faca9163c', 2, N'32636d29-fb1d-48b9-b682-a349df8c5989')
GO

INSERT [dbo].[QuestionNumber] ([Id], [Number], [TestId]) VALUES (N'420ca431-20d1-4a42-b27b-bc1725be4998', 1, N'45eb8782-46bb-4bde-a2a5-2817b9ce4ac3')
GO

INSERT [dbo].[QuestionNumber] ([Id], [Number], [TestId]) VALUES (N'339ccc39-6b79-4750-914a-cb271a920de4', 3, N'4d5e42ff-cbb2-415d-9b40-423d99a81150')
GO

INSERT [dbo].[QuestionNumber] ([Id], [Number], [TestId]) VALUES (N'f753d897-7339-4a49-bb63-dd291679d531', 2, N'95bc9f1b-d73a-4091-a5a9-d3b41c9bd30f')
GO

INSERT [dbo].[Question] ([Id], [Data], [TestId]) VALUES (N'9549e6be-aca2-47c4-a859-03bf25080359', N'Въпрос 12', N'4d5e42ff-cbb2-415d-9b40-423d99a81150')
GO

INSERT [dbo].[Question] ([Id], [Data], [TestId]) VALUES (N'f6b987d2-1aa7-4259-b822-0999257a48f0', N'Въпрос 03', N'45eb8782-46bb-4bde-a2a5-2817b9ce4ac3')
GO

INSERT [dbo].[Question] ([Id], [Data], [TestId]) VALUES (N'38313e78-cf36-4f0f-a353-134be1e4f982', N'Въпрос 34', N'95bc9f1b-d73a-4091-a5a9-d3b41c9bd30f')
GO

INSERT [dbo].[Question] ([Id], [Data], [TestId]) VALUES (N'30cd0c16-298d-4e6a-99d8-18bc51fe9b7e', N'Въпрос 31', N'95bc9f1b-d73a-4091-a5a9-d3b41c9bd30f')
GO

INSERT [dbo].[Question] ([Id], [Data], [TestId]) VALUES (N'ab52b3d8-0cdb-4883-8786-19e38d87f938', N'Въпрос 21', N'32636d29-fb1d-48b9-b682-a349df8c5989')
GO

INSERT [dbo].[Question] ([Id], [Data], [TestId]) VALUES (N'6e4a7c6f-d0e6-4cdf-84af-1c30e7476495', N'Въпрос 45', N'3d685919-2d61-423e-9de5-043346e06ab3')
GO

INSERT [dbo].[Question] ([Id], [Data], [TestId]) VALUES (N'882e0ba2-d42d-40df-932c-216c5bcb18b2', N'Въпрос 22', N'32636d29-fb1d-48b9-b682-a349df8c5989')
GO

INSERT [dbo].[Question] ([Id], [Data], [TestId]) VALUES (N'c4753911-0dda-4801-acb2-2ebe32876352', N'Въпрос 23', N'32636d29-fb1d-48b9-b682-a349df8c5989')
GO

INSERT [dbo].[Question] ([Id], [Data], [TestId]) VALUES (N'334b5fa3-2b51-4bff-a708-2fd1596733cd', N'Въпрос 02', N'45eb8782-46bb-4bde-a2a5-2817b9ce4ac3')
GO

INSERT [dbo].[Question] ([Id], [Data], [TestId]) VALUES (N'dcd48b1a-ab90-458b-a003-4be4799c4330', N'Въпрос 11', N'4d5e42ff-cbb2-415d-9b40-423d99a81150')
GO

INSERT [dbo].[Question] ([Id], [Data], [TestId]) VALUES (N'9c9f3725-d7d3-4090-9992-59450f531a9d', N'Въпрос 33', N'95bc9f1b-d73a-4091-a5a9-d3b41c9bd30f')
GO

INSERT [dbo].[Question] ([Id], [Data], [TestId]) VALUES (N'21ab0275-f547-4319-8055-65942a73e85a', N'Въпрос 05', N'45eb8782-46bb-4bde-a2a5-2817b9ce4ac3')
GO

INSERT [dbo].[Question] ([Id], [Data], [TestId]) VALUES (N'7031b305-f420-430a-9f32-73ab4b781756', N'Въпрос 42', N'3d685919-2d61-423e-9de5-043346e06ab3')
GO

INSERT [dbo].[Question] ([Id], [Data], [TestId]) VALUES (N'77e7a7d8-cc82-4c37-843b-7e23c553b184', N'Въпрос 25', N'32636d29-fb1d-48b9-b682-a349df8c5989')
GO

INSERT [dbo].[Question] ([Id], [Data], [TestId]) VALUES (N'43e0a1c7-0660-46ce-8eb7-8721466d5568', N'Въпрос 43', N'3d685919-2d61-423e-9de5-043346e06ab3')
GO

INSERT [dbo].[Question] ([Id], [Data], [TestId]) VALUES (N'68f17a44-a0e4-48a1-aa70-910592aee2f8', N'Въпрос 14', N'4d5e42ff-cbb2-415d-9b40-423d99a81150')
GO

INSERT [dbo].[Question] ([Id], [Data], [TestId]) VALUES (N'2c5e0288-173e-4fa2-89a1-92c87d131403', N'Въпрос 01', N'45eb8782-46bb-4bde-a2a5-2817b9ce4ac3')
GO

INSERT [dbo].[Question] ([Id], [Data], [TestId]) VALUES (N'9b54cdc8-b6da-4110-8bfe-932c7d3b0504', N'Въпрос 24', N'32636d29-fb1d-48b9-b682-a349df8c5989')
GO

INSERT [dbo].[Question] ([Id], [Data], [TestId]) VALUES (N'f6af958b-4fd3-4af1-ba2e-93bb0d473bee', N'Въпрос 13', N'4d5e42ff-cbb2-415d-9b40-423d99a81150')
GO

INSERT [dbo].[Question] ([Id], [Data], [TestId]) VALUES (N'c671c0c3-c7dd-48b7-946d-9f81d06df3ab', N'Въпрос 44', N'3d685919-2d61-423e-9de5-043346e06ab3')
GO

INSERT [dbo].[Question] ([Id], [Data], [TestId]) VALUES (N'79d8c066-eccc-40b8-b97a-c6a09f625518', N'Въпрос 41', N'3d685919-2d61-423e-9de5-043346e06ab3')
GO

INSERT [dbo].[Question] ([Id], [Data], [TestId]) VALUES (N'3da7930f-d4fe-4b37-bcbd-e83d4ac15ebc', N'Въпрос 04', N'45eb8782-46bb-4bde-a2a5-2817b9ce4ac3')
GO

INSERT [dbo].[Question] ([Id], [Data], [TestId]) VALUES (N'7401b503-58ef-45df-9c08-eba1a1afb83e', N'Въпрос 15', N'4d5e42ff-cbb2-415d-9b40-423d99a81150')
GO

INSERT [dbo].[Question] ([Id], [Data], [TestId]) VALUES (N'8814eb4f-01ec-4ce0-859b-f3e7def18cef', N'Въпрос 35', N'95bc9f1b-d73a-4091-a5a9-d3b41c9bd30f')
GO

INSERT [dbo].[Question] ([Id], [Data], [TestId]) VALUES (N'eec06788-3e51-4e03-914a-ff0944d2974d', N'Въпрос 32', N'95bc9f1b-d73a-4091-a5a9-d3b41c9bd30f')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'420ca431-20d1-4a42-b27b-bc1725be4998', 1, N'2c5e0288-173e-4fa2-89a1-92c87d131403')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'58c04818-e0ab-41d7-9f7d-2c21c8b4e4fd', 1, N'334b5fa3-2b51-4bff-a708-2fd1596733cd')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'b5d5139f-c955-48b2-84c0-240d6d3a3cd7', 1, N'f6b987d2-1aa7-4259-b822-0999257a48f0')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'420ca431-20d1-4a42-b27b-bc1725be4998', 2, N'3da7930f-d4fe-4b37-bcbd-e83d4ac15ebc')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'58c04818-e0ab-41d7-9f7d-2c21c8b4e4fd', 2, N'21ab0275-f547-4319-8055-65942a73e85a')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'b5d5139f-c955-48b2-84c0-240d6d3a3cd7', 2, N'2c5e0288-173e-4fa2-89a1-92c87d131403')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'b5d5139f-c955-48b2-84c0-240d6d3a3cd7', 3, N'334b5fa3-2b51-4bff-a708-2fd1596733cd')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'58c04818-e0ab-41d7-9f7d-2c21c8b4e4fd', 3, N'f6b987d2-1aa7-4259-b822-0999257a48f0')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'420ca431-20d1-4a42-b27b-bc1725be4998', 3, N'2c5e0288-173e-4fa2-89a1-92c87d131403')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'5fef7438-98cc-4479-a0a7-166cf073965c', 3, N'6e4a7c6f-d0e6-4cdf-84af-1c30e7476495')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'0f7e1a70-8f45-4ea4-931e-7236b8aa2965', 3, N'7031b305-f420-430a-9f32-73ab4b781756')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'3557ae09-aa95-4716-9c36-7c3d328b9099', 3, N'43e0a1c7-0660-46ce-8eb7-8721466d5568')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'5fef7438-98cc-4479-a0a7-166cf073965c', 2, N'c671c0c3-c7dd-48b7-946d-9f81d06df3ab')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'0f7e1a70-8f45-4ea4-931e-7236b8aa2965', 2, N'79d8c066-eccc-40b8-b97a-c6a09f625518')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'3557ae09-aa95-4716-9c36-7c3d328b9099', 2, N'6e4a7c6f-d0e6-4cdf-84af-1c30e7476495')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'5fef7438-98cc-4479-a0a7-166cf073965c', 1, N'7031b305-f420-430a-9f32-73ab4b781756')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'0f7e1a70-8f45-4ea4-931e-7236b8aa2965', 1, N'43e0a1c7-0660-46ce-8eb7-8721466d5568')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'3557ae09-aa95-4716-9c36-7c3d328b9099', 1, N'c671c0c3-c7dd-48b7-946d-9f81d06df3ab')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'23121f0b-d446-4af7-a0ed-4739988a349d', 1, N'9549e6be-aca2-47c4-a859-03bf25080359')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'0822e373-6ea9-4bbb-a636-6ff8a7a74957', 1, N'dcd48b1a-ab90-458b-a003-4be4799c4330')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'339ccc39-6b79-4750-914a-cb271a920de4', 1, N'68f17a44-a0e4-48a1-aa70-910592aee2f8')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'23121f0b-d446-4af7-a0ed-4739988a349d', 2, N'f6af958b-4fd3-4af1-ba2e-93bb0d473bee')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'0822e373-6ea9-4bbb-a636-6ff8a7a74957', 2, N'7401b503-58ef-45df-9c08-eba1a1afb83e')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'339ccc39-6b79-4750-914a-cb271a920de4', 2, N'9549e6be-aca2-47c4-a859-03bf25080359')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'23121f0b-d446-4af7-a0ed-4739988a349d', 3, N'dcd48b1a-ab90-458b-a003-4be4799c4330')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'0822e373-6ea9-4bbb-a636-6ff8a7a74957', 3, N'68f17a44-a0e4-48a1-aa70-910592aee2f8')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'339ccc39-6b79-4750-914a-cb271a920de4', 3, N'f6af958b-4fd3-4af1-ba2e-93bb0d473bee')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'758f047d-55af-4da5-acb2-2a03ffff8049', 1, N'ab52b3d8-0cdb-4883-8786-19e38d87f938')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'da511061-dbf4-434e-8963-b59faca9163c', 1, N'882e0ba2-d42d-40df-932c-216c5bcb18b2')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'345cbfd3-10c3-40cc-a01f-43595eb908ad', 1, N'c4753911-0dda-4801-acb2-2ebe32876352')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'758f047d-55af-4da5-acb2-2a03ffff8049', 2, N'77e7a7d8-cc82-4c37-843b-7e23c553b184')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'da511061-dbf4-434e-8963-b59faca9163c', 2, N'9b54cdc8-b6da-4110-8bfe-932c7d3b0504')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'345cbfd3-10c3-40cc-a01f-43595eb908ad', 2, N'ab52b3d8-0cdb-4883-8786-19e38d87f938')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'758f047d-55af-4da5-acb2-2a03ffff8049', 3, N'882e0ba2-d42d-40df-932c-216c5bcb18b2')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'da511061-dbf4-434e-8963-b59faca9163c', 3, N'c4753911-0dda-4801-acb2-2ebe32876352')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'345cbfd3-10c3-40cc-a01f-43595eb908ad', 3, N'77e7a7d8-cc82-4c37-843b-7e23c553b184')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'8a7a4341-e9d9-4d6c-bc2c-3822239666af', 1, N'30cd0c16-298d-4e6a-99d8-18bc51fe9b7e')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'f753d897-7339-4a49-bb63-dd291679d531', 1, N'eec06788-3e51-4e03-914a-ff0944d2974d')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'97e036a6-6fb5-46bf-b957-333143f9d83d', 1, N'9c9f3725-d7d3-4090-9992-59450f531a9d')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'8a7a4341-e9d9-4d6c-bc2c-3822239666af', 2, N'38313e78-cf36-4f0f-a353-134be1e4f982')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'f753d897-7339-4a49-bb63-dd291679d531', 2, N'8814eb4f-01ec-4ce0-859b-f3e7def18cef')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'97e036a6-6fb5-46bf-b957-333143f9d83d', 2, N'30cd0c16-298d-4e6a-99d8-18bc51fe9b7e')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'8a7a4341-e9d9-4d6c-bc2c-3822239666af', 3, N'eec06788-3e51-4e03-914a-ff0944d2974d')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'f753d897-7339-4a49-bb63-dd291679d531', 3, N'9c9f3725-d7d3-4090-9992-59450f531a9d')
GO

INSERT [dbo].[QuestionVariant] ([NumberId], [Variant], [QuestionId]) VALUES (N'97e036a6-6fb5-46bf-b957-333143f9d83d', 3, N'38313e78-cf36-4f0f-a353-134be1e4f982')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'83bbaf86-f63e-48d3-89dd-00f90632f9fd', N'Отговор 38', 0, N'ab52b3d8-0cdb-4883-8786-19e38d87f938')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'b8ec8f23-2185-4cb6-86c9-02c62d087cd7', N'Отговор 51', 0, N'9b54cdc8-b6da-4110-8bfe-932c7d3b0504')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'e08b9001-dcd8-4a0f-a733-033caef12131', N'Отговор 66', 1, N'7031b305-f420-430a-9f32-73ab4b781756')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'89ba3f8d-a8eb-439d-9538-08dac8dedaea', N'Отговор 63', 0, N'43e0a1c7-0660-46ce-8eb7-8721466d5568')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'a2c655ab-36fe-4125-bc9e-093bc11a2ecb', N'Отговор 03', 0, N'2c5e0288-173e-4fa2-89a1-92c87d131403')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'4a075ed9-e33b-47c4-8fc1-0ae3d9f43b1a', N'Отговор 58', 0, N'c671c0c3-c7dd-48b7-946d-9f81d06df3ab')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'b7c16181-70e8-4798-b2f3-118be2d9303e', N'Отговор 48', 0, N'c4753911-0dda-4801-acb2-2ebe32876352')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'7fc1a28c-75ff-4358-8695-176df33bbd9a', N'Отговор 32', 0, N'3da7930f-d4fe-4b37-bcbd-e83d4ac15ebc')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'ec6b9d9c-8c64-490d-a36d-18b71ff9ba3d', N'Отговор 60', 1, N'c671c0c3-c7dd-48b7-946d-9f81d06df3ab')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'8956c50c-115a-4fea-a616-18d4aaa4a440', N'Отговор 37', 0, N'ab52b3d8-0cdb-4883-8786-19e38d87f938')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'ec795a97-41ce-4867-a5ed-1c7cb8d1bbd8', N'Отговор 09', 1, N'9549e6be-aca2-47c4-a859-03bf25080359')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'435f3d27-2470-4142-9ca7-205935ba2a13', N'Отговор 72', 0, N'79d8c066-eccc-40b8-b97a-c6a09f625518')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'cb25d56b-1ccb-452c-8c00-207a0024a91b', N'Отговор 59', 0, N'c671c0c3-c7dd-48b7-946d-9f81d06df3ab')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'252d06e0-bd4e-42d1-b9ab-231cc724448a', N'Отговор 93', 1, N'30cd0c16-298d-4e6a-99d8-18bc51fe9b7e')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'f78cbbfd-4a36-40d6-bf51-28c16094b218', N'Отговор 05', 1, N'dcd48b1a-ab90-458b-a003-4be4799c4330')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'67c504f6-3f92-4448-a479-2986962b7b9a', N'Отговор 42', 0, N'882e0ba2-d42d-40df-932c-216c5bcb18b2')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'f57e271e-308a-44e8-86a3-2b3563ea0fe3', N'Отговор 92', 0, N'eec06788-3e51-4e03-914a-ff0944d2974d')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'b375c236-1217-48e5-b1ae-2e18af4e36f8', N'Отговор 46', 0, N'c4753911-0dda-4801-acb2-2ebe32876352')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'd75b91bb-724b-4981-a086-2ef0a2b51480', N'Отговор 90', 0, N'eec06788-3e51-4e03-914a-ff0944d2974d')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'7a6340d9-af1d-4bfd-8da5-30a31e6a010a', N'Отговор 65', 0, N'7031b305-f420-430a-9f32-73ab4b781756')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'18eb3b95-65d4-4f23-8d3b-32fa1a04611a', N'Отговор 85', 1, N'9c9f3725-d7d3-4090-9992-59450f531a9d')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'd17c01b9-c408-49d3-b87b-38cd0adc4b83', N'Отговор 56', 0, N'6e4a7c6f-d0e6-4cdf-84af-1c30e7476495')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'497fc076-abcb-4d6f-ba99-38cf86147fbf', N'Отговор 77', 1, N'38313e78-cf36-4f0f-a353-134be1e4f982')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'aae89509-acaf-423f-aca5-39aa81d42356', N'Отговор 47', 0, N'c4753911-0dda-4801-acb2-2ebe32876352')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'e381be02-994c-4419-936e-39fca84fa1dd', N'Отговор 27', 0, N'334b5fa3-2b51-4bff-a708-2fd1596733cd')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'976b39b9-5536-4e77-afe4-3e6e28bcb3cf', N'Отговор 08', 0, N'dcd48b1a-ab90-458b-a003-4be4799c4330')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'17cdf502-4402-4d29-b7ae-3ebaceac2651', N'Отговор 61', 1, N'43e0a1c7-0660-46ce-8eb7-8721466d5568')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'a52e81f2-9c6f-40f5-9634-3ed3e35fd593', N'Отговор 24', 0, N'7401b503-58ef-45df-9c08-eba1a1afb83e')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'5a9b1024-5530-4c11-880e-41c8bf1c036d', N'Отговор 86', 0, N'9c9f3725-d7d3-4090-9992-59450f531a9d')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'b34bfb6f-6d70-4cb0-9177-4320b41fd56a', N'Отговор 62', 0, N'43e0a1c7-0660-46ce-8eb7-8721466d5568')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'f1ff4041-ca5b-4b75-853b-447df35fedf5', N'Отговор 75', 0, N'8814eb4f-01ec-4ce0-859b-f3e7def18cef')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'840e217e-c57c-4b0c-9171-45f97e774afa', N'Отговор 19', 0, N'68f17a44-a0e4-48a1-aa70-910592aee2f8')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'a15974a7-974c-4bd3-a219-4ce86dd18ef3', N'Отговор 91', 0, N'eec06788-3e51-4e03-914a-ff0944d2974d')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'f2e41826-48e2-4283-9aad-4fb678c41a19', N'Отговор 21', 1, N'7401b503-58ef-45df-9c08-eba1a1afb83e')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'bfa24de6-f11f-4926-b96d-5458a82e29c5', N'Отговор 53', 1, N'6e4a7c6f-d0e6-4cdf-84af-1c30e7476495')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'f4016689-1efe-4ec7-ab9f-5758276ca1a4', N'Отговор 34', 0, N'21ab0275-f547-4319-8055-65942a73e85a')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'a86307ec-2d46-4952-a07e-584a3d8b4e65', N'Отговор 89', 1, N'eec06788-3e51-4e03-914a-ff0944d2974d')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'5b4f32c5-823e-4b03-a12b-5a0a2f720596', N'Отговор 39', 0, N'ab52b3d8-0cdb-4883-8786-19e38d87f938')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'19985ac6-b1c7-4fed-8517-5e84ad1faaef', N'Отговор 52', 0, N'9b54cdc8-b6da-4110-8bfe-932c7d3b0504')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'4289cb25-95be-4fd2-afe8-5ee56b177008', N'Отговор 88', 1, N'9c9f3725-d7d3-4090-9992-59450f531a9d')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'310385e9-938f-4828-9eb3-616ad99d168e', N'Отговор 95', 0, N'30cd0c16-298d-4e6a-99d8-18bc51fe9b7e')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'c19f010f-c408-421a-a63f-61d8c683b1fd', N'Отговор 01', 1, N'2c5e0288-173e-4fa2-89a1-92c87d131403')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'48e7a209-55e0-42ef-8245-63a9f4adc346', N'Отговор 50', 1, N'9b54cdc8-b6da-4110-8bfe-932c7d3b0504')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'8a02e4f2-0a02-41cf-a351-6475515ee5e5', N'Отговор 25', 0, N'334b5fa3-2b51-4bff-a708-2fd1596733cd')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'9f0f5560-bd05-4c75-98f8-71ba9b8eb73b', N'Отговор 28', 0, N'334b5fa3-2b51-4bff-a708-2fd1596733cd')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'94508f84-57bb-43a0-8822-73f57667032f', N'Отговор 22', 0, N'7401b503-58ef-45df-9c08-eba1a1afb83e')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'abc5b125-acab-48ca-a949-7b628d2847d2', N'Отговор 23', 0, N'7401b503-58ef-45df-9c08-eba1a1afb83e')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'16cb445d-9432-4e5c-b2f6-800e47fff3a6', N'Отговор 73', 0, N'8814eb4f-01ec-4ce0-859b-f3e7def18cef')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'99e52823-2bd9-4f03-a999-801c15453808', N'Отговор 82', 0, N'f6b987d2-1aa7-4259-b822-0999257a48f0')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'd4e852e5-5d15-4845-8c09-80248067b1a9', N'Отговор 76', 0, N'8814eb4f-01ec-4ce0-859b-f3e7def18cef')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'f48a9a0d-dabb-40b4-ae8e-83c9938a4e2e', N'Отговор 12', 0, N'9549e6be-aca2-47c4-a859-03bf25080359')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'07562a32-2fba-4668-a1c3-8669da29451e', N'Отговор 83', 0, N'f6b987d2-1aa7-4259-b822-0999257a48f0')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'f0e54d4b-43c6-4c71-b3fb-8c44ed3a5587', N'Отговор 02', 0, N'2c5e0288-173e-4fa2-89a1-92c87d131403')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'818fce99-b9d3-4fa5-aaad-90f4c656f220', N'Отговор 43', 0, N'882e0ba2-d42d-40df-932c-216c5bcb18b2')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'9260912a-c906-483d-ad2f-92bc28f57b05', N'Отговор 41', 1, N'882e0ba2-d42d-40df-932c-216c5bcb18b2')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'4bc02cad-3a80-4788-b13a-9373e81b0867', N'Отговор 18', 0, N'68f17a44-a0e4-48a1-aa70-910592aee2f8')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'2fd5d02a-e429-47a2-81c8-990097b4a034', N'Отговор 40', 1, N'ab52b3d8-0cdb-4883-8786-19e38d87f938')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'a0c286d3-814e-407b-8c6a-a11f0cd2006b', N'Отговор 26', 1, N'334b5fa3-2b51-4bff-a708-2fd1596733cd')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'ba7f83d4-b3b3-448b-842f-a574c6e9fb69', N'Отговор 67', 0, N'7031b305-f420-430a-9f32-73ab4b781756')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'eb97c876-44c3-483e-9c75-a5f810e65cf6', N'Отговор 10', 0, N'9549e6be-aca2-47c4-a859-03bf25080359')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'7b18a4c4-81fb-489f-9d7b-a7fd0d6407c8', N'Отговор 84', 0, N'f6b987d2-1aa7-4259-b822-0999257a48f0')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'cb24e864-3376-422b-9455-ab9e8706d7b9', N'Отговор 78', 0, N'38313e78-cf36-4f0f-a353-134be1e4f982')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'39e7cab1-6f8f-4f71-8e19-ac701324aae6', N'Отговор 14', 1, N'f6af958b-4fd3-4af1-ba2e-93bb0d473bee')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'8748474d-b1ca-4bbd-a35c-ad5e94a209ed', N'Отговор 44', 0, N'882e0ba2-d42d-40df-932c-216c5bcb18b2')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'c2cee418-ad89-4ba0-9c69-afa401ae8556', N'Отговор 11', 0, N'9549e6be-aca2-47c4-a859-03bf25080359')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'05bb45d0-6d4d-44af-8b25-b0bd1992e872', N'Отговор 07', 0, N'dcd48b1a-ab90-458b-a003-4be4799c4330')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'90b01448-497d-4224-b02c-bbae4d696bcf', N'Отговор 74', 1, N'8814eb4f-01ec-4ce0-859b-f3e7def18cef')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'7a9640b0-53af-48ca-ac13-bf5aa6edf8c5', N'Отговор 79', 0, N'38313e78-cf36-4f0f-a353-134be1e4f982')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'996f053d-04e4-49ed-a1c5-c4527ea60a60', N'Отговор 94', 0, N'30cd0c16-298d-4e6a-99d8-18bc51fe9b7e')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'77a2a6ce-95b4-49d5-8573-c9043a65580d', N'Отговор 35', 0, N'21ab0275-f547-4319-8055-65942a73e85a')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'7f5c4303-b946-498e-8884-ca7ad80f8695', N'Отговор 45', 1, N'c4753911-0dda-4801-acb2-2ebe32876352')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'd995c7ff-e90a-4d68-a12c-cb29e536ea5b', N'Отговор 04', 0, N'2c5e0288-173e-4fa2-89a1-92c87d131403')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'062594e0-e568-4dd8-bdef-cddccd74308a', N'Отговор 13', 0, N'f6af958b-4fd3-4af1-ba2e-93bb0d473bee')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'69794f3a-28d1-457d-84e6-cfde193cc9ec', N'Отговор 57', 0, N'c671c0c3-c7dd-48b7-946d-9f81d06df3ab')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'49f1ac5a-2fd9-4fcf-b1fa-d16e7bd1cb66', N'Отговор 16', 0, N'f6af958b-4fd3-4af1-ba2e-93bb0d473bee')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'1b1894ea-6f06-4dd6-9419-d4293062b8b8', N'Отговор 15', 0, N'f6af958b-4fd3-4af1-ba2e-93bb0d473bee')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'5ec3d5c8-0a0b-4e49-bf8b-d880e2b6c9d9', N'Отговор 68', 0, N'7031b305-f420-430a-9f32-73ab4b781756')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'dd0a08a6-4ab6-4924-a05d-d987663d1a7f', N'Отговор 06', 1, N'dcd48b1a-ab90-458b-a003-4be4799c4330')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'7b804cbb-a480-4996-a7fd-d9efdc9a36f8', N'Отговор 87', 0, N'9c9f3725-d7d3-4090-9992-59450f531a9d')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'2762bccd-5245-4556-a08f-dba8b741cfe1', N'Отговор 20', 0, N'68f17a44-a0e4-48a1-aa70-910592aee2f8')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'71667dc4-c3ca-4dd7-95b9-dc85d0b8e72c', N'Отговор 69', 1, N'79d8c066-eccc-40b8-b97a-c6a09f625518')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'07461386-5dd9-4799-84a2-dcf4fa31f081', N'Отговор 97', 1, N'77e7a7d8-cc82-4c37-843b-7e23c553b184')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'5ca46117-a784-4ea2-bd19-df35ca4fa63d', N'Отговор 17', 1, N'68f17a44-a0e4-48a1-aa70-910592aee2f8')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'42fa8d78-cc02-49f1-9754-e1c22a46b263', N'Отговор 29', 1, N'3da7930f-d4fe-4b37-bcbd-e83d4ac15ebc')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'5d801e80-c7a4-4723-ac09-e4845692ccd5', N'Отговор 31', 0, N'3da7930f-d4fe-4b37-bcbd-e83d4ac15ebc')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'14c37699-e4d0-40ea-94f2-e69ac511c298', N'Отговор 64', 0, N'43e0a1c7-0660-46ce-8eb7-8721466d5568')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'82a632e5-8b5f-4701-89b1-e6dcff64fa4c', N'Отговор 54', 0, N'6e4a7c6f-d0e6-4cdf-84af-1c30e7476495')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'c0c14fa8-ddee-4434-bb45-e87b820bce52', N'Отговор 96', 0, N'30cd0c16-298d-4e6a-99d8-18bc51fe9b7e')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'49bf1d85-09fe-406d-86eb-ebfc91f990cb', N'Отговор 33', 1, N'21ab0275-f547-4319-8055-65942a73e85a')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'b47497c3-2238-45f1-8e91-ecdc9eb665ac', N'Отговор 36', 1, N'21ab0275-f547-4319-8055-65942a73e85a')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'd0791a87-7029-4ee5-b133-eeff6d8b42e6', N'Отговор 70', 0, N'79d8c066-eccc-40b8-b97a-c6a09f625518')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'270560ce-3e50-4218-832c-f05bc64cc974', N'Отговор 00', 1, N'77e7a7d8-cc82-4c37-843b-7e23c553b184')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'da52d79f-a3a1-4be9-ad32-f0fe7ed88730', N'Отговор 49', 0, N'9b54cdc8-b6da-4110-8bfe-932c7d3b0504')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'bce0cc90-2145-47b2-9f29-f22837e785df', N'Отговор 98', 0, N'77e7a7d8-cc82-4c37-843b-7e23c553b184')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'18ab665b-a6b8-4d06-a8ff-f569bece45c6', N'Отговор 71', 0, N'79d8c066-eccc-40b8-b97a-c6a09f625518')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'200f6e8a-1f58-40f3-b867-f7f4d5097837', N'Отговор 80', 1, N'38313e78-cf36-4f0f-a353-134be1e4f982')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'0af33950-2286-4633-b0ba-f812a7a525dd', N'Отговор 55', 0, N'6e4a7c6f-d0e6-4cdf-84af-1c30e7476495')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'3c47bb6b-a831-4b05-baa0-f81e27d9bdd2', N'Отговор 30', 0, N'3da7930f-d4fe-4b37-bcbd-e83d4ac15ebc')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'2d2e72fc-3aee-4e89-bff6-f95f62ccfd9b', N'Отговор 99', 0, N'77e7a7d8-cc82-4c37-843b-7e23c553b184')
GO

INSERT [dbo].[Answer] ([Id], [Data], [IsCorrect], [QuestionId]) VALUES (N'056e2b6f-3798-48cc-89af-ff1ff104a82b', N'Отговор 81', 1, N'f6b987d2-1aa7-4259-b822-0999257a48f0')
GO

INSERT [dbo].[Student] ([Id], [FacultyNumber], [FirstName], [LastName], [Email], [ClassId]) VALUES (N'e1ecb244-ef5b-431e-8c9a-048a4059e5e2', N'12345678', N'Койчо', N'Коев', NULL, N'0f48f35f-53d5-4363-b4fc-e4ac94035d0c')
GO

INSERT [dbo].[Student] ([Id], [FacultyNumber], [FirstName], [LastName], [Email], [ClassId]) VALUES (N'f27365f0-b854-43c8-8057-22e3921326da', N'52689334', N'Асен', N'Асев', N'няма@поща.бг', N'90fa74a9-d876-491b-8e6f-7d2ec9c75a48')
GO

INSERT [dbo].[Student] ([Id], [FacultyNumber], [FirstName], [LastName], [Email], [ClassId]) VALUES (N'89610110-fb9a-4488-85a1-7ea103697935', N'578421596', N'Желязко', N'Желев', N'ид@ввв.ком', N'0f48f35f-53d5-4363-b4fc-e4ac94035d0c')
GO

INSERT [dbo].[Student] ([Id], [FacultyNumber], [FirstName], [LastName], [Email], [ClassId]) VALUES (N'fe6cd9f4-fa18-4f8a-92e9-ba43e39e994c', N'123456781', N'Ивайло', N'Нешев', N'ivo.nesh@abv.bg', N'90fa74a9-d876-491b-8e6f-7d2ec9c75a48')
GO

INSERT [dbo].[Student] ([Id], [FacultyNumber], [FirstName], [LastName], [Email], [ClassId]) VALUES (N'6b4615bb-ca74-416f-8681-d3586dda5def', N'12345679', N'Гинко', N'Гинев', N'ginko@ginev.bg', N'0f48f35f-53d5-4363-b4fc-e4ac94035d0c')
GO

INSERT [dbo].[TestAttempt] ([Id], [StudentId], [Date]) VALUES (N'055d4020-3f77-4c9c-95a7-867165f0e1b8', N'6b4615bb-ca74-416f-8681-d3586dda5def', CAST(N'2020-02-15T00:00:00.0000000' AS DateTime2))
GO

INSERT [dbo].[TestAttempt] ([Id], [StudentId], [Date]) VALUES (N'9a16ebc5-b20d-4cf4-843f-a725fb59e9b8', N'fe6cd9f4-fa18-4f8a-92e9-ba43e39e994c', CAST(N'2010-04-22T00:00:00.0000000' AS DateTime2))
GO

INSERT [dbo].[TestAttempt] ([Id], [StudentId], [Date]) VALUES (N'71058bbc-65bc-456b-b90c-dc14644822f2', N'e1ecb244-ef5b-431e-8c9a-048a4059e5e2', CAST(N'2020-03-11T00:00:00.0000000' AS DateTime2))
GO

INSERT [dbo].[SelectedAnswer] ([Id], [TestAttemptId], [AnswerId]) VALUES (N'ae1e62ed-caba-4013-be80-10ea48adc4b5', N'9a16ebc5-b20d-4cf4-843f-a725fb59e9b8', N'f2e41826-48e2-4283-9aad-4fb678c41a19')
GO

INSERT [dbo].[SelectedAnswer] ([Id], [TestAttemptId], [AnswerId]) VALUES (N'b869299d-9e1b-4dd6-b7a7-131f233edb15', N'71058bbc-65bc-456b-b90c-dc14644822f2', N'cb25d56b-1ccb-452c-8c00-207a0024a91b')
GO

INSERT [dbo].[SelectedAnswer] ([Id], [TestAttemptId], [AnswerId]) VALUES (N'd599ae4b-d92f-4e63-b65c-277ff065bf0c', N'9a16ebc5-b20d-4cf4-843f-a725fb59e9b8', N'062594e0-e568-4dd8-bdef-cddccd74308a')
GO

INSERT [dbo].[SelectedAnswer] ([Id], [TestAttemptId], [AnswerId]) VALUES (N'c9eef31c-dab6-4317-a470-4a474bce5618', N'055d4020-3f77-4c9c-95a7-867165f0e1b8', N'42fa8d78-cc02-49f1-9754-e1c22a46b263')
GO

INSERT [dbo].[SelectedAnswer] ([Id], [TestAttemptId], [AnswerId]) VALUES (N'7a70dead-2626-462d-a19f-588a76705aca', N'055d4020-3f77-4c9c-95a7-867165f0e1b8', N'49bf1d85-09fe-406d-86eb-ebfc91f990cb')
GO

INSERT [dbo].[SelectedAnswer] ([Id], [TestAttemptId], [AnswerId]) VALUES (N'9d58283c-0b79-4434-8c17-887b57866f83', N'71058bbc-65bc-456b-b90c-dc14644822f2', N'b34bfb6f-6d70-4cb0-9177-4320b41fd56a')
GO

INSERT [dbo].[SelectedAnswer] ([Id], [TestAttemptId], [AnswerId]) VALUES (N'b9334ead-8a72-4d2f-80a4-a00703f89661', N'71058bbc-65bc-456b-b90c-dc14644822f2', N'e08b9001-dcd8-4a0f-a733-033caef12131')
GO

INSERT [dbo].[SelectedAnswer] ([Id], [TestAttemptId], [AnswerId]) VALUES (N'5f874fba-d46d-4ac3-aa3b-c4e7fa7d955a', N'055d4020-3f77-4c9c-95a7-867165f0e1b8', N'77a2a6ce-95b4-49d5-8573-c9043a65580d')
GO

INSERT [dbo].[SelectedAnswer] ([Id], [TestAttemptId], [AnswerId]) VALUES (N'7b5bc6d2-03e2-433c-b6d0-c84c6d2e19ff', N'055d4020-3f77-4c9c-95a7-867165f0e1b8', N'a2c655ab-36fe-4125-bc9e-093bc11a2ecb')
GO

INSERT [dbo].[SelectedAnswer] ([Id], [TestAttemptId], [AnswerId]) VALUES (N'8b5cf785-9744-4b4f-a3eb-f185e0fcca10', N'9a16ebc5-b20d-4cf4-843f-a725fb59e9b8', N'ec795a97-41ce-4867-a5ed-1c7cb8d1bbd8')
GO
