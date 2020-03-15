CREATE TABLE [dbo].[QuestionNumber] (
    [Id]     UNIQUEIDENTIFIER CONSTRAINT [DF_QuestionNumber_Id] DEFAULT (newid()) NOT NULL,
    [Number] INT              NOT NULL,
    [TestId] UNIQUEIDENTIFIER NOT NULL,
    CONSTRAINT [PK_QuestionNumber] PRIMARY KEY CLUSTERED ([Id] ASC),
    CONSTRAINT [FK_QuestionNumber_Test] FOREIGN KEY ([TestId]) REFERENCES [dbo].[Test] ([Id])
);


GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE   TRIGGER dbo.InsertNewQuestion 
   ON  dbo.QuestionNumber 
   INSTEAD OF INSERT
AS 
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for trigger here

	DECLARE @TestId NVARCHAR(50)
	DECLARE @Number INT
	DECLARE @QuestionCount INT
 
    SELECT @TestId = INSERTED.TestId
    FROM INSERTED

	SELECT @QuestionCount = QuestionCount
	FROM dbo.Test
	WHERE Id = @TestId

	SELECT @Number = COUNT(Id)
	FROM dbo.QuestionNumber
	WHERE TestId = @TestId

	IF (@Number = @QuestionCount)
		BEGIN
			RAISERROR ('Question Number Limit reached', 10, 1)
			ROLLBACK TRANSACTION
		END 
	ELSE
		BEGIN
			INSERT INTO dbo.QuestionNumber (TestId, Number)
			VALUES (@TestId, @Number + 1);
		END 
END
