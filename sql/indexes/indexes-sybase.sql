create index IX_93D5AD4E on Address (companyId)
go
create index IX_ABD7DAC0 on Address (companyId, classNameId)
go
create index IX_71CB1123 on Address (companyId, classNameId, classPK)
go
create index IX_5BC8B0D4 on Address (userId)
go
create index IX_381E55DA on Address (uuid_)
go
create index IX_8FCB620E on Address (uuid_, companyId)
go

create index IX_6EDB9600 on AnnouncementsDelivery (userId)
go
create unique index IX_BA4413D5 on AnnouncementsDelivery (userId, type_)
go

create index IX_A6EF0B81 on AnnouncementsEntry (classNameId, classPK)
go
create index IX_D49C2E66 on AnnouncementsEntry (userId)
go
create index IX_1AFBDE08 on AnnouncementsEntry (uuid_)
go
create index IX_F2949120 on AnnouncementsEntry (uuid_, companyId)
go

create index IX_9C7EB9F on AnnouncementsFlag (entryId)
go
create unique index IX_4539A99C on AnnouncementsFlag (userId, entryId, value)
go

create index IX_E639E2F6 on AssetCategory (groupId)
go
create index IX_C7F39FCA on AssetCategory (groupId, name, vocabularyId)
go
create index IX_852EA801 on AssetCategory (groupId, parentCategoryId, name, vocabularyId)
go
create index IX_87603842 on AssetCategory (groupId, parentCategoryId, vocabularyId)
go
create index IX_2008FACB on AssetCategory (groupId, vocabularyId)
go
create index IX_D61ABE08 on AssetCategory (name, vocabularyId)
go
create index IX_7BB1826B on AssetCategory (parentCategoryId)
go
create index IX_9DDD15EA on AssetCategory (parentCategoryId, name)
go
create unique index IX_BE4DF2BF on AssetCategory (parentCategoryId, name, vocabularyId)
go
create index IX_B185E980 on AssetCategory (parentCategoryId, vocabularyId)
go
create index IX_4D37BB00 on AssetCategory (uuid_)
go
create index IX_BBAF6928 on AssetCategory (uuid_, companyId)
go
create unique index IX_E8D019AA on AssetCategory (uuid_, groupId)
go
create index IX_287B1F89 on AssetCategory (vocabularyId)
go

create index IX_99DA856 on AssetCategoryProperty (categoryId)
go
create unique index IX_DBD111AA on AssetCategoryProperty (categoryId, key_)
go
create index IX_8654719F on AssetCategoryProperty (companyId)
go
create index IX_52340033 on AssetCategoryProperty (companyId, key_)
go

create index IX_A188F560 on AssetEntries_AssetCategories (categoryId)
go
create index IX_E119938A on AssetEntries_AssetCategories (entryId)
go

create index IX_2ED82CAD on AssetEntries_AssetTags (entryId)
go
create index IX_B2A61B55 on AssetEntries_AssetTags (tagId)
go

create unique index IX_1E9D371D on AssetEntry (classNameId, classPK)
go
create index IX_FC1F9C7B on AssetEntry (classUuid)
go
create index IX_7306C60 on AssetEntry (companyId)
go
create index IX_75D42FF9 on AssetEntry (expirationDate)
go
create index IX_1EBA6821 on AssetEntry (groupId, classUuid)
go
create index IX_FEC4A201 on AssetEntry (layoutUuid)
go
create index IX_2E4E3885 on AssetEntry (publishDate)
go

create index IX_128516C8 on AssetLink (entryId1)
go
create index IX_56E0AB21 on AssetLink (entryId1, entryId2)
go
create unique index IX_8F542794 on AssetLink (entryId1, entryId2, type_)
go
create index IX_14D5A20D on AssetLink (entryId1, type_)
go
create index IX_12851A89 on AssetLink (entryId2)
go
create index IX_91F132C on AssetLink (entryId2, type_)
go

create index IX_7C9E46BA on AssetTag (groupId)
go
create index IX_D63322F9 on AssetTag (groupId, name)
go

create index IX_DFF1F063 on AssetTagProperty (companyId)
go
create index IX_13805BF7 on AssetTagProperty (companyId, key_)
go
create index IX_3269E180 on AssetTagProperty (tagId)
go
create unique index IX_2C944354 on AssetTagProperty (tagId, key_)
go

create index IX_50702693 on AssetTagStats (classNameId)
go
create index IX_9464CA on AssetTagStats (tagId)
go
create unique index IX_56682CC4 on AssetTagStats (tagId, classNameId)
go

create index IX_B22D908C on AssetVocabulary (companyId)
go
create index IX_B6B8CA0E on AssetVocabulary (groupId)
go
create index IX_C0AAD74D on AssetVocabulary (groupId, name)
go
create index IX_55F58818 on AssetVocabulary (uuid_)
go
create index IX_C4E6FD10 on AssetVocabulary (uuid_, companyId)
go
create unique index IX_1B2B8792 on AssetVocabulary (uuid_, groupId)
go

create index IX_C5A6C78F on BackgroundTask (companyId)
go
create index IX_5A09E5D1 on BackgroundTask (groupId)
go
create index IX_98CC0AAB on BackgroundTask (groupId, name, taskExecutorClassName)
go
create index IX_C71C3B7 on BackgroundTask (groupId, status)
go
create index IX_A73B688A on BackgroundTask (groupId, taskExecutorClassName)
go
create index IX_7E757D70 on BackgroundTask (groupId, taskExecutorClassName, status)
go
create index IX_C07CC7F8 on BackgroundTask (name)
go
create index IX_75638CDF on BackgroundTask (status)
go
create index IX_2FCFE748 on BackgroundTask (taskExecutorClassName, status)
go

create index IX_72EF6041 on BlogsEntry (companyId)
go
create index IX_430D791F on BlogsEntry (companyId, displayDate)
go
create index IX_BB0C2905 on BlogsEntry (companyId, displayDate, status)
go
create index IX_EB2DCE27 on BlogsEntry (companyId, status)
go
create index IX_8CACE77B on BlogsEntry (companyId, userId)
go
create index IX_A5F57B61 on BlogsEntry (companyId, userId, status)
go
create index IX_2672F77F on BlogsEntry (displayDate, status)
go
create index IX_81A50303 on BlogsEntry (groupId)
go
create index IX_621E19D on BlogsEntry (groupId, displayDate)
go
create index IX_F0E73383 on BlogsEntry (groupId, displayDate, status)
go
create index IX_1EFD8EE9 on BlogsEntry (groupId, status)
go
create unique index IX_DB780A20 on BlogsEntry (groupId, urlTitle)
go
create index IX_FBDE0AA3 on BlogsEntry (groupId, userId, displayDate)
go
create index IX_DA04F689 on BlogsEntry (groupId, userId, displayDate, status)
go
create index IX_49E15A23 on BlogsEntry (groupId, userId, status)
go
create index IX_69157A4D on BlogsEntry (uuid_)
go
create index IX_5E8307BB on BlogsEntry (uuid_, companyId)
go
create unique index IX_1B1040FD on BlogsEntry (uuid_, groupId)
go

create index IX_90CDA39A on BlogsStatsUser (companyId, entryCount)
go
create index IX_43840EEB on BlogsStatsUser (groupId)
go
create index IX_28C78D5C on BlogsStatsUser (groupId, entryCount)
go
create unique index IX_82254C25 on BlogsStatsUser (groupId, userId)
go
create index IX_BB51F1D9 on BlogsStatsUser (userId)
go
create index IX_507BA031 on BlogsStatsUser (userId, lastPostDate)
go

create index IX_1F90CA2D on BookmarksEntry (companyId)
go
create index IX_276C8C13 on BookmarksEntry (companyId, status)
go
create index IX_5200100C on BookmarksEntry (groupId, folderId)
go
create index IX_146382F2 on BookmarksEntry (groupId, folderId, status)
go
create index IX_416AD7D5 on BookmarksEntry (groupId, status)
go
create index IX_C78B61AC on BookmarksEntry (groupId, userId, folderId, status)
go
create index IX_9D9CF70F on BookmarksEntry (groupId, userId, status)
go
create index IX_E848278F on BookmarksEntry (resourceBlockId)
go
create index IX_B670BA39 on BookmarksEntry (uuid_)
go
create index IX_89BEDC4F on BookmarksEntry (uuid_, companyId)
go
create unique index IX_EAA02A91 on BookmarksEntry (uuid_, groupId)
go

create index IX_2ABA25D7 on BookmarksFolder (companyId)
go
create index IX_C27C9DBD on BookmarksFolder (companyId, status)
go
create index IX_7F703619 on BookmarksFolder (groupId)
go
create index IX_967799C0 on BookmarksFolder (groupId, parentFolderId)
go
create index IX_D16018A6 on BookmarksFolder (groupId, parentFolderId, status)
go
create index IX_28A49BB9 on BookmarksFolder (resourceBlockId)
go
create index IX_451E7AE3 on BookmarksFolder (uuid_)
go
create index IX_54F0ED65 on BookmarksFolder (uuid_, companyId)
go
create unique index IX_DC2F8927 on BookmarksFolder (uuid_, groupId)
go

create unique index IX_E7B95510 on BrowserTracker (userId)
go

create index IX_D6FD9496 on CalEvent (companyId)
go
create index IX_12EE4898 on CalEvent (groupId)
go
create index IX_FCD7C63D on CalEvent (groupId, type_)
go
create index IX_F6006202 on CalEvent (remindBy)
go
create index IX_C1AD2122 on CalEvent (uuid_)
go
create index IX_299639C6 on CalEvent (uuid_, companyId)
go
create unique index IX_5CCE79C8 on CalEvent (uuid_, groupId)
go

create unique index IX_B27A301F on ClassName_ (value)
go

create index IX_38EFE3FD on Company (logoId)
go
create index IX_12566EC2 on Company (mx)
go
create unique index IX_EC00543C on Company (webId)
go

create index IX_B8C28C53 on Contact_ (accountId)
go
create index IX_791914FA on Contact_ (classNameId, classPK)
go
create index IX_66D496A3 on Contact_ (companyId)
go

create unique index IX_717B97E1 on Country (a2)
go
create unique index IX_717B9BA2 on Country (a3)
go
create unique index IX_19DA007B on Country (name)
go

create index IX_6A6C1C85 on DDLRecord (companyId)
go
create index IX_87A6B599 on DDLRecord (recordSetId)
go
create index IX_AAC564D3 on DDLRecord (recordSetId, userId)
go
create index IX_8BC2F891 on DDLRecord (uuid_)
go
create index IX_384AB6F7 on DDLRecord (uuid_, companyId)
go
create unique index IX_B4328F39 on DDLRecord (uuid_, groupId)
go

create index IX_4FA5969F on DDLRecordSet (groupId)
go
create unique index IX_56DAB121 on DDLRecordSet (groupId, recordSetKey)
go
create index IX_561E44E9 on DDLRecordSet (uuid_)
go
create index IX_5938C39F on DDLRecordSet (uuid_, companyId)
go
create unique index IX_270BA5E1 on DDLRecordSet (uuid_, groupId)
go

create index IX_2F4DDFE1 on DDLRecordVersion (recordId)
go
create index IX_762ADC7 on DDLRecordVersion (recordId, status)
go
create unique index IX_C79E347 on DDLRecordVersion (recordId, version)
go

create index IX_E3BAF436 on DDMContent (companyId)
go
create index IX_50BF1038 on DDMContent (groupId)
go
create index IX_AE4B50C2 on DDMContent (uuid_)
go
create index IX_3A9C0626 on DDMContent (uuid_, companyId)
go
create unique index IX_EB9BDE28 on DDMContent (uuid_, groupId)
go

create unique index IX_702D1AD5 on DDMStorageLink (classPK)
go
create index IX_81776090 on DDMStorageLink (structureId)
go
create index IX_32A18526 on DDMStorageLink (uuid_)
go

create index IX_31817A62 on DDMStructure (classNameId)
go
create index IX_4FBAC092 on DDMStructure (companyId, classNameId)
go
create index IX_C8419FBE on DDMStructure (groupId)
go
create index IX_B6ED5E50 on DDMStructure (groupId, classNameId)
go
create unique index IX_C8785130 on DDMStructure (groupId, classNameId, structureKey)
go
create index IX_43395316 on DDMStructure (groupId, parentStructureId)
go
create index IX_657899A8 on DDMStructure (parentStructureId)
go
create index IX_20FDE04C on DDMStructure (structureKey)
go
create index IX_E61809C8 on DDMStructure (uuid_)
go
create index IX_F9FB8D60 on DDMStructure (uuid_, companyId)
go
create unique index IX_85C7EBE2 on DDMStructure (uuid_, groupId)
go

create index IX_D43E4208 on DDMStructureLink (classNameId)
go
create unique index IX_C803899D on DDMStructureLink (classPK)
go
create index IX_17692B58 on DDMStructureLink (structureId)
go

create index IX_B6356F93 on DDMTemplate (classNameId, classPK, type_)
go
create index IX_32F83D16 on DDMTemplate (classPK)
go
create index IX_DB24DDDD on DDMTemplate (groupId)
go
create index IX_BD9A4A91 on DDMTemplate (groupId, classNameId)
go
create index IX_824ADC72 on DDMTemplate (groupId, classNameId, classPK)
go
create index IX_90800923 on DDMTemplate (groupId, classNameId, classPK, type_)
go
create index IX_F0C3449 on DDMTemplate (groupId, classNameId, classPK, type_, mode_)
go
create unique index IX_E6DFAB84 on DDMTemplate (groupId, classNameId, templateKey)
go
create index IX_B1C33EA6 on DDMTemplate (groupId, classPK)
go
create index IX_33BEF579 on DDMTemplate (language)
go
create index IX_127A35B0 on DDMTemplate (smallImageId)
go
create index IX_CAE41A28 on DDMTemplate (templateKey)
go
create index IX_C4F283C8 on DDMTemplate (type_)
go
create index IX_F2A243A7 on DDMTemplate (uuid_)
go
create index IX_D4C2C221 on DDMTemplate (uuid_, companyId)
go
create unique index IX_1AA75CE3 on DDMTemplate (uuid_, groupId)
go

create index IX_6A83A66A on DLContent (companyId, repositoryId)
go
create index IX_EB531760 on DLContent (companyId, repositoryId, path_)
go
create unique index IX_FDD1AAA8 on DLContent (companyId, repositoryId, path_, version)
go

create index IX_4CB1B2B4 on DLFileEntry (companyId)
go
create index IX_772ECDE7 on DLFileEntry (fileEntryTypeId)
go
create index IX_8F6C75D0 on DLFileEntry (folderId, name)
go
create index IX_F4AF5636 on DLFileEntry (groupId)
go
create index IX_93CF8193 on DLFileEntry (groupId, folderId)
go
create index IX_29D0AF28 on DLFileEntry (groupId, folderId, fileEntryTypeId)
go
create unique index IX_5391712 on DLFileEntry (groupId, folderId, name)
go
create unique index IX_ED5CA615 on DLFileEntry (groupId, folderId, title)
go
create index IX_43261870 on DLFileEntry (groupId, userId)
go
create index IX_D20C434D on DLFileEntry (groupId, userId, folderId)
go
create index IX_D9492CF6 on DLFileEntry (mimeType)
go
create index IX_1B352F4A on DLFileEntry (repositoryId, folderId)
go
create index IX_64F0FE40 on DLFileEntry (uuid_)
go
create index IX_31079DE8 on DLFileEntry (uuid_, companyId)
go
create unique index IX_BC2E7E6A on DLFileEntry (uuid_, groupId)
go

create unique index IX_7332B44F on DLFileEntryMetadata (DDMStructureId, fileVersionId)
go
create index IX_4F40FE5E on DLFileEntryMetadata (fileEntryId)
go
create index IX_A44636C9 on DLFileEntryMetadata (fileEntryId, fileVersionId)
go
create index IX_F8E90438 on DLFileEntryMetadata (fileEntryTypeId)
go
create index IX_1FE9C04 on DLFileEntryMetadata (fileVersionId)
go
create index IX_D49AB5D1 on DLFileEntryMetadata (uuid_)
go

create index IX_4501FD9C on DLFileEntryType (groupId)
go
create unique index IX_5B6BEF5F on DLFileEntryType (groupId, fileEntryTypeKey)
go
create index IX_90724726 on DLFileEntryType (uuid_)
go
create index IX_5B03E942 on DLFileEntryType (uuid_, companyId)
go
create unique index IX_1399D844 on DLFileEntryType (uuid_, groupId)
go

create index IX_8373EC7C on DLFileEntryTypes_DDMStructures (fileEntryTypeId)
go
create index IX_F147CF3F on DLFileEntryTypes_DDMStructures (structureId)
go

create index IX_5BB6AD6C on DLFileEntryTypes_DLFolders (fileEntryTypeId)
go
create index IX_6E00A2EC on DLFileEntryTypes_DLFolders (folderId)
go

create unique index IX_38F0315 on DLFileRank (companyId, userId, fileEntryId)
go
create index IX_A65A1F8B on DLFileRank (fileEntryId)
go
create index IX_BAFB116E on DLFileRank (groupId, userId)
go
create index IX_EED06670 on DLFileRank (userId)
go

create index IX_A4BB2E58 on DLFileShortcut (companyId)
go
create index IX_8571953E on DLFileShortcut (companyId, status)
go
create index IX_B0051937 on DLFileShortcut (groupId, folderId)
go
create index IX_4B7247F6 on DLFileShortcut (toFileEntryId)
go
create index IX_4831EBE4 on DLFileShortcut (uuid_)
go
create index IX_29AE81C4 on DLFileShortcut (uuid_, companyId)
go
create unique index IX_FDB4A946 on DLFileShortcut (uuid_, groupId)
go

create index IX_F389330E on DLFileVersion (companyId)
go
create index IX_A0A283F4 on DLFileVersion (companyId, status)
go
create index IX_C68DC967 on DLFileVersion (fileEntryId)
go
create index IX_D47BB14D on DLFileVersion (fileEntryId, status)
go
create unique index IX_E2815081 on DLFileVersion (fileEntryId, version)
go
create index IX_DFD809D3 on DLFileVersion (groupId, folderId, status)
go
create index IX_9BE769ED on DLFileVersion (groupId, folderId, title, version)
go
create index IX_FFB3395C on DLFileVersion (mimeType)
go
create index IX_4BFABB9A on DLFileVersion (uuid_)
go
create index IX_95E9E44E on DLFileVersion (uuid_, companyId)
go
create unique index IX_C99B2650 on DLFileVersion (uuid_, groupId)
go

create index IX_A74DB14C on DLFolder (companyId)
go
create index IX_E79BE432 on DLFolder (companyId, status)
go
create index IX_F2EA1ACE on DLFolder (groupId)
go
create index IX_49C37475 on DLFolder (groupId, parentFolderId)
go
create unique index IX_902FD874 on DLFolder (groupId, parentFolderId, name)
go
create index IX_51556082 on DLFolder (parentFolderId, name)
go
create index IX_EE29C715 on DLFolder (repositoryId)
go
create index IX_CBC408D8 on DLFolder (uuid_)
go
create index IX_DA448450 on DLFolder (uuid_, companyId)
go
create unique index IX_3CC1DED2 on DLFolder (uuid_, groupId)
go

create index IX_3D8E1607 on DLSyncEvent (modifiedTime)
go
create unique index IX_57D82B06 on DLSyncEvent (typePK)
go

create index IX_1BB072CA on EmailAddress (companyId)
go
create index IX_49D2DEC4 on EmailAddress (companyId, classNameId)
go
create index IX_551A519F on EmailAddress (companyId, classNameId, classPK)
go
create index IX_7B43CD8 on EmailAddress (userId)
go
create index IX_D24F3956 on EmailAddress (uuid_)
go
create index IX_F74AB912 on EmailAddress (uuid_, companyId)
go

create index IX_A8C0CBE8 on ExpandoColumn (tableId)
go
create unique index IX_FEFC8DA7 on ExpandoColumn (tableId, name)
go

create index IX_49EB3118 on ExpandoRow (classPK)
go
create index IX_D3F5D7AE on ExpandoRow (tableId)
go
create unique index IX_81EFBFF5 on ExpandoRow (tableId, classPK)
go

create index IX_B5AE8A85 on ExpandoTable (companyId, classNameId)
go
create unique index IX_37562284 on ExpandoTable (companyId, classNameId, name)
go

create index IX_B29FEF17 on ExpandoValue (classNameId, classPK)
go
create index IX_F7DD0987 on ExpandoValue (columnId)
go
create unique index IX_9DDD21E5 on ExpandoValue (columnId, rowId_)
go
create index IX_9112A7A0 on ExpandoValue (rowId_)
go
create index IX_F0566A77 on ExpandoValue (tableId)
go
create index IX_1BD3F4C on ExpandoValue (tableId, classPK)
go
create index IX_CA9AFB7C on ExpandoValue (tableId, columnId)
go
create unique index IX_D27B03E7 on ExpandoValue (tableId, columnId, classPK)
go
create index IX_B71E92D5 on ExpandoValue (tableId, rowId_)
go

create index IX_ABA5CEC2 on Group_ (companyId)
go
create index IX_B584B5CC on Group_ (companyId, classNameId)
go
create unique index IX_D0D5E397 on Group_ (companyId, classNameId, classPK)
go
create unique index IX_5DE0BE11 on Group_ (companyId, classNameId, liveGroupId, name)
go
create index IX_ABE2D54 on Group_ (companyId, classNameId, parentGroupId)
go
create unique index IX_5BDDB872 on Group_ (companyId, friendlyURL)
go
create unique index IX_BBCA55B on Group_ (companyId, liveGroupId, name)
go
create unique index IX_5AA68501 on Group_ (companyId, name)
go
create index IX_5D75499E on Group_ (companyId, parentGroupId)
go
create index IX_16218A38 on Group_ (liveGroupId)
go
create index IX_F981514E on Group_ (uuid_)
go
create index IX_26CC761A on Group_ (uuid_, companyId)
go
create unique index IX_754FBB1C on Group_ (uuid_, groupId)
go

create index IX_75267DCA on Groups_Orgs (groupId)
go
create index IX_6BBB7682 on Groups_Orgs (organizationId)
go

create index IX_84471FD2 on Groups_Roles (groupId)
go
create index IX_3103EF3D on Groups_Roles (roleId)
go

create index IX_31FB749A on Groups_UserGroups (groupId)
go
create index IX_3B69160F on Groups_UserGroups (userGroupId)
go

create index IX_6A925A4D on Image (size_)
go

create index IX_FF0E7A72 on JournalArticle (classNameId, templateId)
go
create index IX_DFF98523 on JournalArticle (companyId)
go
create index IX_323DF109 on JournalArticle (companyId, status)
go
create index IX_3D070845 on JournalArticle (companyId, version)
go
create index IX_E82F322B on JournalArticle (companyId, version, status)
go
create index IX_EA05E9E1 on JournalArticle (displayDate, status)
go
create index IX_9356F865 on JournalArticle (groupId)
go
create index IX_68C0F69C on JournalArticle (groupId, articleId)
go
create index IX_4D5CD982 on JournalArticle (groupId, articleId, status)
go
create unique index IX_85C52EEC on JournalArticle (groupId, articleId, version)
go
create index IX_9CE6E0FA on JournalArticle (groupId, classNameId, classPK)
go
create index IX_A2534AC2 on JournalArticle (groupId, classNameId, layoutUuid)
go
create index IX_91E78C35 on JournalArticle (groupId, classNameId, structureId)
go
create index IX_F43B9FF2 on JournalArticle (groupId, classNameId, templateId)
go
create index IX_5CD17502 on JournalArticle (groupId, folderId)
go
create index IX_F35391E8 on JournalArticle (groupId, folderId, status)
go
create index IX_3C028C1E on JournalArticle (groupId, layoutUuid)
go
create index IX_301D024B on JournalArticle (groupId, status)
go
create index IX_2E207659 on JournalArticle (groupId, structureId)
go
create index IX_8DEAE14E on JournalArticle (groupId, templateId)
go
create index IX_22882D02 on JournalArticle (groupId, urlTitle)
go
create index IX_D2D249E8 on JournalArticle (groupId, urlTitle, status)
go
create index IX_D19C1B9F on JournalArticle (groupId, userId)
go
create index IX_43A0F80F on JournalArticle (groupId, userId, classNameId)
go
create index IX_3F1EA19E on JournalArticle (layoutUuid)
go
create index IX_33F49D16 on JournalArticle (resourcePrimKey)
go
create index IX_3E2765FC on JournalArticle (resourcePrimKey, status)
go
create index IX_EF9B7028 on JournalArticle (smallImageId)
go
create index IX_8E8710D9 on JournalArticle (structureId)
go
create index IX_9106F6CE on JournalArticle (templateId)
go
create index IX_F029602F on JournalArticle (uuid_)
go
create index IX_71520099 on JournalArticle (uuid_, companyId)
go
create unique index IX_3463D95B on JournalArticle (uuid_, groupId)
go

create index IX_3B51BB68 on JournalArticleImage (groupId)
go
create index IX_158B526F on JournalArticleImage (groupId, articleId, version)
go
create unique index IX_103D6207 on JournalArticleImage (groupId, articleId, version, elInstanceId, elName, languageId)
go

create index IX_F8433677 on JournalArticleResource (groupId)
go
create unique index IX_88DF994A on JournalArticleResource (groupId, articleId)
go
create index IX_DCD1FAC1 on JournalArticleResource (uuid_)
go
create unique index IX_84AB0309 on JournalArticleResource (uuid_, groupId)
go

create index IX_9207CB31 on JournalContentSearch (articleId)
go
create index IX_6838E427 on JournalContentSearch (groupId, articleId)
go
create index IX_8DAF8A35 on JournalContentSearch (portletId)
go

create index IX_35A2DB2F on JournalFeed (groupId)
go
create unique index IX_65576CBC on JournalFeed (groupId, feedId)
go
create index IX_50C36D79 on JournalFeed (uuid_)
go
create index IX_CB37A10F on JournalFeed (uuid_, companyId)
go
create unique index IX_39031F51 on JournalFeed (uuid_, groupId)
go

create index IX_E6E2725D on JournalFolder (companyId)
go
create index IX_C36B0443 on JournalFolder (companyId, status)
go
create index IX_742DEC1F on JournalFolder (groupId)
go
create index IX_E988689E on JournalFolder (groupId, name)
go
create index IX_190483C6 on JournalFolder (groupId, parentFolderId)
go
create unique index IX_65026705 on JournalFolder (groupId, parentFolderId, name)
go
create index IX_EFD9CAC on JournalFolder (groupId, parentFolderId, status)
go
create index IX_63BDFA69 on JournalFolder (uuid_)
go
create index IX_54F89E1F on JournalFolder (uuid_, companyId)
go
create unique index IX_E002061 on JournalFolder (uuid_, groupId)
go

create index IX_C7FBC998 on Layout (companyId)
go
create index IX_C099D61A on Layout (groupId)
go
create index IX_23922F7D on Layout (iconImageId)
go
create index IX_B529BFD3 on Layout (layoutPrototypeUuid)
go
create index IX_39A18ECC on Layout (sourcePrototypeLayoutUuid)
go
create index IX_D0822724 on Layout (uuid_)
go
create index IX_2CE4BE84 on Layout (uuid_, companyId)
go

create index IX_6C226433 on LayoutBranch (layoutSetBranchId)
go
create index IX_2C42603E on LayoutBranch (layoutSetBranchId, plid)
go
create unique index IX_FD57097D on LayoutBranch (layoutSetBranchId, plid, name)
go

create index IX_EAB317C8 on LayoutFriendlyURL (companyId)
go
create index IX_742EF04A on LayoutFriendlyURL (groupId)
go
create index IX_83AE56AB on LayoutFriendlyURL (plid)
go
create index IX_59051329 on LayoutFriendlyURL (plid, friendlyURL)
go
create unique index IX_C5762E72 on LayoutFriendlyURL (plid, languageId)
go
create index IX_9F80D54 on LayoutFriendlyURL (uuid_)
go
create index IX_F4321A54 on LayoutFriendlyURL (uuid_, companyId)
go
create unique index IX_326525D6 on LayoutFriendlyURL (uuid_, groupId)
go

create index IX_30616AAA on LayoutPrototype (companyId)
go
create index IX_CEF72136 on LayoutPrototype (uuid_)
go
create index IX_63ED2532 on LayoutPrototype (uuid_, companyId)
go

create index IX_314B621A on LayoutRevision (layoutSetBranchId)
go
create index IX_13984800 on LayoutRevision (layoutSetBranchId, layoutBranchId, plid)
go
create index IX_4A84AF43 on LayoutRevision (layoutSetBranchId, parentLayoutRevisionId, plid)
go
create index IX_B7B914E5 on LayoutRevision (layoutSetBranchId, plid)
go
create index IX_70DA9ECB on LayoutRevision (layoutSetBranchId, plid, status)
go
create index IX_7FFAE700 on LayoutRevision (layoutSetBranchId, status)
go
create index IX_9329C9D6 on LayoutRevision (plid)
go
create index IX_8EC3D2BC on LayoutRevision (plid, status)
go

create index IX_A40B8BEC on LayoutSet (groupId)
go
create index IX_72BBA8B7 on LayoutSet (layoutSetPrototypeUuid)
go

create index IX_8FF5D6EA on LayoutSetBranch (groupId)
go

create index IX_55F63D98 on LayoutSetPrototype (companyId)
go
create index IX_C5D69B24 on LayoutSetPrototype (uuid_)
go
create index IX_D9FFCA84 on LayoutSetPrototype (uuid_, companyId)
go

create index IX_2932DD37 on ListType (type_)
go

create unique index IX_228562AD on Lock_ (className, key_)
go
create index IX_E3F1286B on Lock_ (expirationDate)
go
create index IX_13C5CD3A on Lock_ (uuid_)
go
create index IX_2C418EAE on Lock_ (uuid_, companyId)
go

create index IX_69951A25 on MBBan (banUserId)
go
create index IX_5C3FF12A on MBBan (groupId)
go
create unique index IX_8ABC4E3B on MBBan (groupId, banUserId)
go
create index IX_48814BBA on MBBan (userId)
go
create index IX_8A13C634 on MBBan (uuid_)
go
create index IX_4F841574 on MBBan (uuid_, companyId)
go
create unique index IX_2A3B68F6 on MBBan (uuid_, groupId)
go

create index IX_BC735DCF on MBCategory (companyId)
go
create index IX_E15A5DB5 on MBCategory (companyId, status)
go
create index IX_BB870C11 on MBCategory (groupId)
go
create index IX_ED292508 on MBCategory (groupId, parentCategoryId)
go
create index IX_C295DBEE on MBCategory (groupId, parentCategoryId, status)
go
create index IX_DA84A9F7 on MBCategory (groupId, status)
go
create index IX_C2626EDB on MBCategory (uuid_)
go
create index IX_13DF4E6D on MBCategory (uuid_, companyId)
go
create unique index IX_F7D28C2F on MBCategory (uuid_, groupId)
go

create index IX_79D0120B on MBDiscussion (classNameId)
go
create unique index IX_33A4DE38 on MBDiscussion (classNameId, classPK)
go
create unique index IX_B5CA2DC on MBDiscussion (threadId)
go
create index IX_5477D431 on MBDiscussion (uuid_)
go
create index IX_7E965757 on MBDiscussion (uuid_, companyId)
go
create unique index IX_F7AAC799 on MBDiscussion (uuid_, groupId)
go

create unique index IX_76CE9CDD on MBMailingList (groupId, categoryId)
go
create index IX_4115EC7A on MBMailingList (uuid_)
go
create index IX_FC61676E on MBMailingList (uuid_, companyId)
go
create unique index IX_E858F170 on MBMailingList (uuid_, groupId)
go

create index IX_51A8D44D on MBMessage (classNameId, classPK)
go
create index IX_F6687633 on MBMessage (classNameId, classPK, status)
go
create index IX_B1432D30 on MBMessage (companyId)
go
create index IX_1AD93C16 on MBMessage (companyId, status)
go
create index IX_5B153FB2 on MBMessage (groupId)
go
create index IX_1073AB9F on MBMessage (groupId, categoryId)
go
create index IX_4257DB85 on MBMessage (groupId, categoryId, status)
go
create index IX_B674AB58 on MBMessage (groupId, categoryId, threadId)
go
create index IX_385E123E on MBMessage (groupId, categoryId, threadId, status)
go
create index IX_ED39AC98 on MBMessage (groupId, status)
go
create index IX_8EB8C5EC on MBMessage (groupId, userId)
go
create index IX_377858D2 on MBMessage (groupId, userId, status)
go
create index IX_75B95071 on MBMessage (threadId)
go
create index IX_A7038CD7 on MBMessage (threadId, parentMessageId)
go
create index IX_9DC8E57 on MBMessage (threadId, status)
go
create index IX_7A040C32 on MBMessage (userId)
go
create index IX_59F9CE5C on MBMessage (userId, classNameId)
go
create index IX_ABEB6D07 on MBMessage (userId, classNameId, classPK)
go
create index IX_4A4BB4ED on MBMessage (userId, classNameId, classPK, status)
go
create index IX_3321F142 on MBMessage (userId, classNameId, status)
go
create index IX_C57B16BC on MBMessage (uuid_)
go
create index IX_57CA9FEC on MBMessage (uuid_, companyId)
go
create unique index IX_8D12316E on MBMessage (uuid_, groupId)
go

create index IX_A00A898F on MBStatsUser (groupId)
go
create unique index IX_9168E2C9 on MBStatsUser (groupId, userId)
go
create index IX_D33A5445 on MBStatsUser (groupId, userId, messageCount)
go
create index IX_847F92B5 on MBStatsUser (userId)
go

create index IX_41F6DC8A on MBThread (categoryId, priority)
go
create index IX_95C0EA45 on MBThread (groupId)
go
create index IX_9A2D11B2 on MBThread (groupId, categoryId)
go
create index IX_50F1904A on MBThread (groupId, categoryId, lastPostDate)
go
create index IX_485F7E98 on MBThread (groupId, categoryId, status)
go
create index IX_E1E7142B on MBThread (groupId, status)
go
create index IX_AEDD9CB5 on MBThread (lastPostDate, priority)
go
create index IX_CC993ECB on MBThread (rootMessageId)
go
create index IX_7E264A0F on MBThread (uuid_)
go
create index IX_F8CA2AB9 on MBThread (uuid_, companyId)
go
create unique index IX_3A200B7B on MBThread (uuid_, groupId)
go

create index IX_8CB0A24A on MBThreadFlag (threadId)
go
create index IX_A28004B on MBThreadFlag (userId)
go
create unique index IX_33781904 on MBThreadFlag (userId, threadId)
go
create index IX_F36BBB83 on MBThreadFlag (uuid_)
go
create index IX_DCE308C5 on MBThreadFlag (uuid_, companyId)
go
create unique index IX_FEB0FC87 on MBThreadFlag (uuid_, groupId)
go

create index IX_FD90786C on MDRAction (ruleGroupInstanceId)
go
create index IX_77BB5E9D on MDRAction (uuid_)
go
create index IX_C58A516B on MDRAction (uuid_, companyId)
go
create unique index IX_75BE36AD on MDRAction (uuid_, groupId)
go

create index IX_4F4293F1 on MDRRule (ruleGroupId)
go
create index IX_EA63B9D7 on MDRRule (uuid_)
go
create index IX_7DEA8DF1 on MDRRule (uuid_, companyId)
go
create unique index IX_F3EFDCB3 on MDRRule (uuid_, groupId)
go

create index IX_5849891C on MDRRuleGroup (groupId)
go
create index IX_7F26B2A6 on MDRRuleGroup (uuid_)
go
create index IX_CC14DC2 on MDRRuleGroup (uuid_, companyId)
go
create unique index IX_46665CC4 on MDRRuleGroup (uuid_, groupId)
go

create index IX_C95A08D8 on MDRRuleGroupInstance (classNameId, classPK)
go
create unique index IX_808A0036 on MDRRuleGroupInstance (classNameId, classPK, ruleGroupId)
go
create index IX_AFF28547 on MDRRuleGroupInstance (groupId)
go
create index IX_22DAB85C on MDRRuleGroupInstance (groupId, classNameId, classPK)
go
create index IX_BF3E642B on MDRRuleGroupInstance (ruleGroupId)
go
create index IX_B6A6BD91 on MDRRuleGroupInstance (uuid_)
go
create index IX_25C9D1F7 on MDRRuleGroupInstance (uuid_, companyId)
go
create unique index IX_9CBC6A39 on MDRRuleGroupInstance (uuid_, groupId)
go

create index IX_8A1CC4B on MembershipRequest (groupId)
go
create index IX_C28C72EC on MembershipRequest (groupId, statusId)
go
create index IX_35AA8FA6 on MembershipRequest (groupId, userId, statusId)
go
create index IX_66D70879 on MembershipRequest (userId)
go

create index IX_4A527DD3 on OrgGroupRole (groupId)
go
create index IX_AB044D1C on OrgGroupRole (roleId)
go

create index IX_6AF0D434 on OrgLabor (organizationId)
go

create index IX_834BCEB6 on Organization_ (companyId)
go
create unique index IX_E301BDF5 on Organization_ (companyId, name)
go
create index IX_418E4522 on Organization_ (companyId, parentOrganizationId)
go
create index IX_396D6B42 on Organization_ (uuid_)
go
create index IX_A9D85BA6 on Organization_ (uuid_, companyId)
go

create index IX_8FEE65F5 on PasswordPolicy (companyId)
go
create unique index IX_3FBFA9F4 on PasswordPolicy (companyId, name)
go
create index IX_51437A01 on PasswordPolicy (uuid_)
go
create index IX_E4D7EF87 on PasswordPolicy (uuid_, companyId)
go

create unique index IX_C3A17327 on PasswordPolicyRel (classNameId, classPK)
go
create index IX_CD25266E on PasswordPolicyRel (passwordPolicyId)
go

create index IX_326F75BD on PasswordTracker (userId)
go

create index IX_9F704A14 on Phone (companyId)
go
create index IX_A2E4AFBA on Phone (companyId, classNameId)
go
create index IX_9A53569 on Phone (companyId, classNameId, classPK)
go
create index IX_F202B9CE on Phone (userId)
go
create index IX_EA6245A0 on Phone (uuid_)
go
create index IX_B271FA88 on Phone (uuid_, companyId)
go

create index IX_B9746445 on PluginSetting (companyId)
go
create unique index IX_7171B2E8 on PluginSetting (companyId, pluginId, pluginType)
go

create index IX_EC370F10 on PollsChoice (questionId)
go
create unique index IX_D76DD2CF on PollsChoice (questionId, name)
go
create index IX_6660B399 on PollsChoice (uuid_)
go
create index IX_8AE746EF on PollsChoice (uuid_, companyId)
go
create unique index IX_C222BD31 on PollsChoice (uuid_, groupId)
go

create index IX_9FF342EA on PollsQuestion (groupId)
go
create index IX_51F087F4 on PollsQuestion (uuid_)
go
create index IX_F910BBB4 on PollsQuestion (uuid_, companyId)
go
create unique index IX_F3C9F36 on PollsQuestion (uuid_, groupId)
go

create index IX_D5DF7B54 on PollsVote (choiceId)
go
create index IX_12112599 on PollsVote (questionId)
go
create unique index IX_1BBFD4D3 on PollsVote (questionId, userId)
go
create index IX_FD32EB70 on PollsVote (uuid_)
go
create index IX_7D8E92B8 on PollsVote (uuid_, companyId)
go
create unique index IX_A88C673A on PollsVote (uuid_, groupId)
go

create index IX_D1F795F1 on PortalPreferences (ownerId, ownerType)
go

create index IX_80CC9508 on Portlet (companyId)
go
create unique index IX_12B5E51D on Portlet (companyId, portletId)
go

create index IX_96BDD537 on PortletItem (groupId, classNameId)
go
create index IX_D699243F on PortletItem (groupId, name, portletId, classNameId)
go
create index IX_2C61314E on PortletItem (groupId, portletId)
go
create index IX_E922D6C0 on PortletItem (groupId, portletId, classNameId)
go
create index IX_8E71167F on PortletItem (groupId, portletId, classNameId, name)
go
create index IX_33B8CE8D on PortletItem (groupId, portletId, name)
go

create index IX_E4F13E6E on PortletPreferences (ownerId, ownerType, plid)
go
create unique index IX_C7057FF7 on PortletPreferences (ownerId, ownerType, plid, portletId)
go
create index IX_C9A3FCE2 on PortletPreferences (ownerId, ownerType, portletId)
go
create index IX_D5EDA3A1 on PortletPreferences (ownerType, plid, portletId)
go
create index IX_A3B2A80C on PortletPreferences (ownerType, portletId)
go
create index IX_F15C1C4F on PortletPreferences (plid)
go
create index IX_D340DB76 on PortletPreferences (plid, portletId)
go
create index IX_8E6DA3A1 on PortletPreferences (portletId)
go

create index IX_16184D57 on RatingsEntry (classNameId, classPK)
go
create index IX_A1A8CB8B on RatingsEntry (classNameId, classPK, score)
go
create unique index IX_B47E3C11 on RatingsEntry (userId, classNameId, classPK)
go

create unique index IX_A6E99284 on RatingsStats (classNameId, classPK)
go

create index IX_16D87CA7 on Region (countryId)
go
create unique index IX_A2635F5C on Region (countryId, regionCode)
go

create unique index IX_8BD6BCA7 on Release_ (servletContextName)
go

create index IX_5253B1FA on Repository (groupId)
go
create unique index IX_60C8634C on Repository (groupId, name, portletId)
go
create index IX_74C17B04 on Repository (uuid_)
go
create index IX_F543EA4 on Repository (uuid_, companyId)
go
create unique index IX_11641E26 on Repository (uuid_, groupId)
go

create index IX_B7034B27 on RepositoryEntry (repositoryId)
go
create unique index IX_9BDCF489 on RepositoryEntry (repositoryId, mappedId)
go
create index IX_B9B1506 on RepositoryEntry (uuid_)
go
create index IX_D3B9AF62 on RepositoryEntry (uuid_, companyId)
go
create unique index IX_354AA664 on RepositoryEntry (uuid_, groupId)
go

create index IX_81F2DB09 on ResourceAction (name)
go
create unique index IX_EDB9986E on ResourceAction (name, actionId)
go

create index IX_DA30B086 on ResourceBlock (companyId, groupId, name)
go
create unique index IX_AEEA209C on ResourceBlock (companyId, groupId, name, permissionsHash)
go
create index IX_2D4CC782 on ResourceBlock (companyId, name)
go

create index IX_4AB3756 on ResourceBlockPermission (resourceBlockId)
go
create unique index IX_D63D20BB on ResourceBlockPermission (resourceBlockId, roleId)
go
create index IX_20A2E3D9 on ResourceBlockPermission (roleId)
go

create index IX_60B99860 on ResourcePermission (companyId, name, scope)
go
create index IX_2200AA69 on ResourcePermission (companyId, name, scope, primKey)
go
create unique index IX_8D83D0CE on ResourcePermission (companyId, name, scope, primKey, roleId)
go
create index IX_26284944 on ResourcePermission (companyId, primKey)
go
create index IX_A37A0588 on ResourcePermission (roleId)
go
create index IX_F4555981 on ResourcePermission (scope)
go

create unique index IX_BA497163 on ResourceTypePermission (companyId, groupId, name, roleId)
go
create index IX_7D81F66F on ResourceTypePermission (companyId, name, roleId)
go
create index IX_A82690E2 on ResourceTypePermission (roleId)
go

create index IX_449A10B9 on Role_ (companyId)
go
create unique index IX_A88E424E on Role_ (companyId, classNameId, classPK)
go
create unique index IX_EBC931B8 on Role_ (companyId, name)
go
create index IX_F3E1C6FC on Role_ (companyId, type_)
go
create index IX_F436EC8E on Role_ (name)
go
create index IX_5EB4E2FB on Role_ (subtype)
go
create index IX_F92B66E6 on Role_ (type_)
go
create index IX_CBE204 on Role_ (type_, subtype)
go
create index IX_26DB26C5 on Role_ (uuid_)
go
create index IX_B9FF6043 on Role_ (uuid_, companyId)
go

create index IX_3BB93ECA on SCFrameworkVersi_SCProductVers (frameworkVersionId)
go
create index IX_E8D33FF9 on SCFrameworkVersi_SCProductVers (productVersionId)
go

create index IX_C98C0D78 on SCFrameworkVersion (companyId)
go
create index IX_272991FA on SCFrameworkVersion (groupId)
go


create index IX_27006638 on SCLicenses_SCProductEntries (licenseId)
go
create index IX_D7710A66 on SCLicenses_SCProductEntries (productEntryId)
go

create index IX_5D25244F on SCProductEntry (companyId)
go
create index IX_72F87291 on SCProductEntry (groupId)
go
create index IX_98E6A9CB on SCProductEntry (groupId, userId)
go
create index IX_7311E812 on SCProductEntry (repoGroupId, repoArtifactId)
go

create index IX_AE8224CC on SCProductScreenshot (fullImageId)
go
create index IX_467956FD on SCProductScreenshot (productEntryId)
go
create index IX_DA913A55 on SCProductScreenshot (productEntryId, priority)
go
create index IX_6C572DAC on SCProductScreenshot (thumbnailId)
go

create index IX_7020130F on SCProductVersion (directDownloadURL)
go
create index IX_8377A211 on SCProductVersion (productEntryId)
go

create index IX_7338606F on ServiceComponent (buildNamespace)
go
create unique index IX_4F0315B8 on ServiceComponent (buildNamespace, buildNumber)
go

create index IX_DA5F4359 on Shard (classNameId, classPK)
go
create index IX_941BA8C3 on Shard (name)
go

create index IX_C28B41DC on ShoppingCart (groupId)
go
create unique index IX_FC46FE16 on ShoppingCart (groupId, userId)
go
create index IX_54101CC8 on ShoppingCart (userId)
go

create index IX_5F615D3E on ShoppingCategory (groupId)
go
create index IX_6A84467D on ShoppingCategory (groupId, name)
go
create index IX_1E6464F5 on ShoppingCategory (groupId, parentCategoryId)
go

create unique index IX_DC60CFAE on ShoppingCoupon (code_)
go
create index IX_3251AF16 on ShoppingCoupon (groupId)
go

create unique index IX_1C717CA6 on ShoppingItem (companyId, sku)
go
create index IX_FEFE7D76 on ShoppingItem (groupId, categoryId)
go
create index IX_903DC750 on ShoppingItem (largeImageId)
go
create index IX_D217AB30 on ShoppingItem (mediumImageId)
go
create index IX_FF203304 on ShoppingItem (smallImageId)
go

create index IX_6D5F9B87 on ShoppingItemField (itemId)
go

create index IX_EA6FD516 on ShoppingItemPrice (itemId)
go

create index IX_1D15553E on ShoppingOrder (groupId)
go
create index IX_119B5630 on ShoppingOrder (groupId, userId, ppPaymentStatus)
go
create unique index IX_D7D6E87A on ShoppingOrder (number_)
go
create index IX_F474FD89 on ShoppingOrder (ppTxnId)
go

create index IX_B5F82C7A on ShoppingOrderItem (orderId)
go

create index IX_F542E9BC on SocialActivity (activitySetId)
go
create index IX_82E39A0C on SocialActivity (classNameId)
go
create index IX_A853C757 on SocialActivity (classNameId, classPK)
go
create index IX_D0E9029E on SocialActivity (classNameId, classPK, type_)
go
create index IX_64B1BC66 on SocialActivity (companyId)
go
create index IX_2A2468 on SocialActivity (groupId)
go
create index IX_FB604DC7 on SocialActivity (groupId, userId, classNameId, classPK, type_, receiverUserId)
go
create unique index IX_8F32DEC9 on SocialActivity (groupId, userId, createDate, classNameId, classPK, type_, receiverUserId)
go
create index IX_1271F25F on SocialActivity (mirrorActivityId)
go
create index IX_1F00C374 on SocialActivity (mirrorActivityId, classNameId, classPK)
go
create index IX_121CA3CB on SocialActivity (receiverUserId)
go
create index IX_3504B8BC on SocialActivity (userId)
go

create index IX_E14B1F1 on SocialActivityAchievement (groupId)
go
create index IX_8F6408F0 on SocialActivityAchievement (groupId, name)
go
create index IX_C8FD892B on SocialActivityAchievement (groupId, userId)
go
create unique index IX_D4390CAA on SocialActivityAchievement (groupId, userId, name)
go

create index IX_A4B9A23B on SocialActivityCounter (classNameId, classPK)
go
create index IX_D6666704 on SocialActivityCounter (groupId)
go
create unique index IX_1B7E3B67 on SocialActivityCounter (groupId, classNameId, classPK, name, ownerType, endPeriod)
go
create unique index IX_374B35AE on SocialActivityCounter (groupId, classNameId, classPK, name, ownerType, startPeriod)
go
create index IX_926CDD04 on SocialActivityCounter (groupId, classNameId, classPK, ownerType)
go

create index IX_B15863FA on SocialActivityLimit (classNameId, classPK)
go
create index IX_18D4BAE5 on SocialActivityLimit (groupId)
go
create unique index IX_F1C1A617 on SocialActivityLimit (groupId, userId, classNameId, classPK, activityType, activityCounterName)
go
create index IX_6F9EDE9F on SocialActivityLimit (userId)
go

create index IX_4460FA14 on SocialActivitySet (classNameId, classPK, type_)
go
create index IX_9E13F2DE on SocialActivitySet (groupId)
go
create index IX_9BE30DDF on SocialActivitySet (groupId, userId, classNameId, type_)
go
create index IX_F71071BD on SocialActivitySet (groupId, userId, type_)
go
create index IX_F80C4386 on SocialActivitySet (userId)
go
create index IX_62AC101A on SocialActivitySet (userId, classNameId, classPK, type_)
go

create index IX_8BE5F230 on SocialActivitySetting (groupId)
go
create index IX_384788CD on SocialActivitySetting (groupId, activityType)
go
create index IX_9D22151E on SocialActivitySetting (groupId, classNameId)
go
create index IX_1E9CF33B on SocialActivitySetting (groupId, classNameId, activityType)
go
create index IX_D984AABA on SocialActivitySetting (groupId, classNameId, activityType, name)
go

create index IX_61171E99 on SocialRelation (companyId)
go
create index IX_95135D1C on SocialRelation (companyId, type_)
go
create index IX_C31A64C6 on SocialRelation (type_)
go
create index IX_5A40CDCC on SocialRelation (userId1)
go
create index IX_4B52BE89 on SocialRelation (userId1, type_)
go
create index IX_B5C9C690 on SocialRelation (userId1, userId2)
go
create unique index IX_12A92145 on SocialRelation (userId1, userId2, type_)
go
create index IX_5A40D18D on SocialRelation (userId2)
go
create index IX_3F9C2FA8 on SocialRelation (userId2, type_)
go
create index IX_F0CA24A5 on SocialRelation (uuid_)
go
create index IX_5B30F663 on SocialRelation (uuid_, companyId)
go

create index IX_D3425487 on SocialRequest (classNameId, classPK, type_, receiverUserId, status)
go
create index IX_A90FE5A0 on SocialRequest (companyId)
go
create index IX_32292ED1 on SocialRequest (receiverUserId)
go
create index IX_D9380CB7 on SocialRequest (receiverUserId, status)
go
create index IX_80F7A9C2 on SocialRequest (userId)
go
create unique index IX_36A90CA7 on SocialRequest (userId, classNameId, classPK, type_, receiverUserId)
go
create index IX_CC86A444 on SocialRequest (userId, classNameId, classPK, type_, status)
go
create index IX_AB5906A8 on SocialRequest (userId, status)
go
create index IX_49D5872C on SocialRequest (uuid_)
go
create index IX_8D42897C on SocialRequest (uuid_, companyId)
go
create unique index IX_4F973EFE on SocialRequest (uuid_, groupId)
go

create index IX_786D171A on Subscription (companyId, classNameId, classPK)
go
create unique index IX_2E1A92D4 on Subscription (companyId, userId, classNameId, classPK)
go
create index IX_54243AFD on Subscription (userId)
go
create index IX_E8F34171 on Subscription (userId, classNameId)
go

create index IX_72D73D39 on SystemEvent (groupId)
go
create index IX_7A2F0ECE on SystemEvent (groupId, classNameId, classPK)
go
create index IX_FFCBB747 on SystemEvent (groupId, classNameId, classPK, type_)
go
create index IX_A19C89FF on SystemEvent (groupId, systemEventSetKey)
go

create index IX_AE6E9907 on Team (groupId)
go
create unique index IX_143DC786 on Team (groupId, name)
go

create index IX_1E8DFB2E on Ticket (classNameId, classPK, type_)
go
create index IX_B2468446 on Ticket (key_)
go

create unique index IX_B35F73D5 on TrashEntry (classNameId, classPK)
go
create index IX_2674F2A8 on TrashEntry (companyId)
go
create index IX_526A032A on TrashEntry (groupId)
go
create index IX_FC4EEA64 on TrashEntry (groupId, classNameId)
go
create index IX_6CAAE2E8 on TrashEntry (groupId, createDate)
go

create index IX_630A643B on TrashVersion (classNameId, classPK)
go
create index IX_55D44577 on TrashVersion (entryId)
go
create index IX_72D58D37 on TrashVersion (entryId, classNameId)
go
create unique index IX_D639348C on TrashVersion (entryId, classNameId, classPK)
go

create index IX_524FEFCE on UserGroup (companyId)
go
create unique index IX_23EAD0D on UserGroup (companyId, name)
go
create index IX_69771487 on UserGroup (companyId, parentUserGroupId)
go
create index IX_5F1DD85A on UserGroup (uuid_)
go
create index IX_72394F8E on UserGroup (uuid_, companyId)
go

create index IX_CCBE4063 on UserGroupGroupRole (groupId)
go
create index IX_CAB0CCC8 on UserGroupGroupRole (groupId, roleId)
go
create index IX_1CDF88C on UserGroupGroupRole (roleId)
go
create index IX_DCDED558 on UserGroupGroupRole (userGroupId)
go
create index IX_73C52252 on UserGroupGroupRole (userGroupId, groupId)
go

create index IX_1B988D7A on UserGroupRole (groupId)
go
create index IX_871412DF on UserGroupRole (groupId, roleId)
go
create index IX_887A2C95 on UserGroupRole (roleId)
go
create index IX_887BE56A on UserGroupRole (userId)
go
create index IX_4D040680 on UserGroupRole (userId, groupId)
go

create index IX_31FB0B08 on UserGroups_Teams (teamId)
go
create index IX_7F187E63 on UserGroups_Teams (userGroupId)
go

create unique index IX_41A32E0D on UserIdMapper (type_, externalUserId)
go
create index IX_E60EA987 on UserIdMapper (userId)
go
create unique index IX_D1C44A6E on UserIdMapper (userId, type_)
go

create index IX_C648700A on UserNotificationDelivery (userId)
go
create unique index IX_8B6E3ACE on UserNotificationDelivery (userId, portletId, classNameId, notificationType, deliveryType)
go

create index IX_3E5D78C4 on UserNotificationEvent (userId)
go
create index IX_ECD8CFEA on UserNotificationEvent (uuid_)
go
create index IX_A6BAFDFE on UserNotificationEvent (uuid_, companyId)
go

create index IX_29BA1CF5 on UserTracker (companyId)
go
create index IX_46B0AE8E on UserTracker (sessionId)
go
create index IX_E4EFBA8D on UserTracker (userId)
go

create index IX_14D8BCC0 on UserTrackerPath (userTrackerId)
go

create index IX_3A1E834E on User_ (companyId)
go
create index IX_740C4D0C on User_ (companyId, createDate)
go
create index IX_BCFDA257 on User_ (companyId, createDate, modifiedDate)
go
create unique index IX_615E9F7A on User_ (companyId, emailAddress)
go
create index IX_1D731F03 on User_ (companyId, facebookId)
go
create index IX_EE8ABD19 on User_ (companyId, modifiedDate)
go
create index IX_89509087 on User_ (companyId, openId)
go
create unique index IX_C5806019 on User_ (companyId, screenName)
go
create index IX_F6039434 on User_ (companyId, status)
go
create unique index IX_9782AD88 on User_ (companyId, userId)
go
create unique index IX_5ADBE171 on User_ (contactId)
go
create index IX_762F63C6 on User_ (emailAddress)
go
create index IX_A18034A4 on User_ (portraitId)
go
create index IX_E0422BDA on User_ (uuid_)
go
create index IX_405CC0E on User_ (uuid_, companyId)
go

create index IX_C4F9E699 on Users_Groups (groupId)
go
create index IX_F10B6C6B on Users_Groups (userId)
go

create index IX_7EF4EC0E on Users_Orgs (organizationId)
go
create index IX_FB646CA6 on Users_Orgs (userId)
go

create index IX_C19E5F31 on Users_Roles (roleId)
go
create index IX_C1A01806 on Users_Roles (userId)
go

create index IX_4D06AD51 on Users_Teams (teamId)
go
create index IX_A098EFBF on Users_Teams (userId)
go

create index IX_66FF2503 on Users_UserGroups (userGroupId)
go
create index IX_BE8102D6 on Users_UserGroups (userId)
go

create unique index IX_A083D394 on VirtualHost (companyId, layoutSetId)
go
create unique index IX_431A3960 on VirtualHost (hostname)
go

create unique index IX_97DFA146 on WebDAVProps (classNameId, classPK)
go

create index IX_96F07007 on Website (companyId)
go
create index IX_4F0F0CA7 on Website (companyId, classNameId)
go
create index IX_F960131C on Website (companyId, classNameId, classPK)
go
create index IX_F75690BB on Website (userId)
go
create index IX_76F15D13 on Website (uuid_)
go
create index IX_712BCD35 on Website (uuid_, companyId)
go

create index IX_5D6FE3F0 on WikiNode (companyId)
go
create index IX_B54332D6 on WikiNode (companyId, status)
go
create index IX_B480A672 on WikiNode (groupId)
go
create unique index IX_920CD8B1 on WikiNode (groupId, name)
go
create index IX_23325358 on WikiNode (groupId, status)
go
create index IX_6C112D7C on WikiNode (uuid_)
go
create index IX_E0E6D12C on WikiNode (uuid_, companyId)
go
create unique index IX_7609B2AE on WikiNode (uuid_, groupId)
go

create index IX_A2001730 on WikiPage (format)
go
create index IX_941E429C on WikiPage (groupId, nodeId, status)
go
create index IX_CAA451D6 on WikiPage (groupId, userId, nodeId, status)
go
create index IX_C8A9C476 on WikiPage (nodeId)
go
create index IX_46EEF3C8 on WikiPage (nodeId, parentTitle)
go
create index IX_1ECC7656 on WikiPage (nodeId, redirectTitle)
go
create index IX_546F2D5C on WikiPage (nodeId, status)
go
create index IX_997EEDD2 on WikiPage (nodeId, title)
go
create index IX_BEA33AB8 on WikiPage (nodeId, title, status)
go
create unique index IX_3D4AF476 on WikiPage (nodeId, title, version)
go
create index IX_85E7CC76 on WikiPage (resourcePrimKey)
go
create index IX_B771D67 on WikiPage (resourcePrimKey, nodeId)
go
create index IX_94D1054D on WikiPage (resourcePrimKey, nodeId, status)
go
create unique index IX_2CD67C81 on WikiPage (resourcePrimKey, nodeId, version)
go
create index IX_1725355C on WikiPage (resourcePrimKey, status)
go
create index IX_FBBE7C96 on WikiPage (userId, nodeId, status)
go
create index IX_9C0E478F on WikiPage (uuid_)
go
create index IX_5DC4BD39 on WikiPage (uuid_, companyId)
go
create unique index IX_899D3DFB on WikiPage (uuid_, groupId)
go

create unique index IX_21277664 on WikiPageResource (nodeId, title)
go
create index IX_BE898221 on WikiPageResource (uuid_)
go

create index IX_A8B0D276 on WorkflowDefinitionLink (companyId)
go
create index IX_A4DB1F0F on WorkflowDefinitionLink (companyId, workflowDefinitionName, workflowDefinitionVersion)
go
create index IX_B6EE8C9E on WorkflowDefinitionLink (groupId, companyId, classNameId)
go
create index IX_1E5B9905 on WorkflowDefinitionLink (groupId, companyId, classNameId, classPK)
go
create index IX_705B40EE on WorkflowDefinitionLink (groupId, companyId, classNameId, classPK, typePK)
go

create index IX_415A7007 on WorkflowInstanceLink (groupId, companyId, classNameId, classPK)
go
