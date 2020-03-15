CREATE TABLE [dbo].[QuestionVariant] (
    [NumberId]   UNIQUEIDENTIFIER NOT NULL,
    [Variant]    INT              NOT NULL,
    [QuestionId] UNIQUEIDENTIFIER NOT NULL,
    CONSTRAINT [FK_VarainantsQuestions_Question] FOREIGN KEY ([QuestionId]) REFERENCES [dbo].[Question] ([Id]),
    CONSTRAINT [FK_VarainantsQuestions_QuestionVariant] FOREIGN KEY ([NumberId]) REFERENCES [dbo].[QuestionNumber] ([Id])
);


GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE   TRIGGER dbo.InsertNewQuestionVariant
   ON  dbo.QuestionVariant 
   INSTEAD OF INSERT
AS 
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for trigger here

	DECLARE @TestId NVARCHAR(50)
	DECLARE @QuestionId NVARCHAR(50)
	DECLARE @NumberId NVARCHAR(50)
	DECLARE @Variations INT
	DECLARE @QuestionVariations INT
 
    SELECT @NumberId = INSERTED.NumberId, @QuestionId = INSERTED.QuestionId
    FROM INSERTED
 
	SELECT @TestId = qn.TestId,  @QuestionVariations = t.QuestionVariations
	FROM dbo.QuestionNumber as qn
		INNER JOIN dbo.Test as t ON t.Id = qn.TestId
	WHERE qn.Id = @NumberId

	SELECT @QuestionVariations = QuestionVariations
	FROM dbo.Test
	WHERE Id = @TestId

	SELECT @Variations = COUNT(NumberId)
	FROM dbo.QuestionVariant
	WHERE NumberId = @NumberId

	IF (@Variations = @QuestionVariations)
		BEGIN
			RAISERROR ('Question Variations Limit reached', 10, 1)
			ROLLBACK TRANSACTION
		END 
	ELSE
		BEGIN
			INSERT INTO dbo.QuestionVariant (NumberId, QuestionId, Variant)
			VALUES (@NumberId, @QuestionId, @Variations + 1);
		END 
END
